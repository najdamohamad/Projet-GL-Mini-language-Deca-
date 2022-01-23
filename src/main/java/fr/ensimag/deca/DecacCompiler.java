package fr.ensimag.deca;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.deca.context.EnvironmentType;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.syntax.DecaLexer;
import fr.ensimag.deca.syntax.DecaParser;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tree.AbstractProgram;
import fr.ensimag.deca.tree.LocationException;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Decac compiler instance.
 * <p>
 * This class is to be instantiated once per source file to be compiled. It
 * contains the meta-data used for compiling (source file name, compilation
 * options) and the necessary utilities for compilation (symbol tables, abstract
 * representation of target file, ...).
 * <p>
 * It contains several objects specialized for different tasks. Delegate methods
 * are used to simplify the code of the caller (e.g. call
 * compiler.addInstruction() instead of compiler.getProgram().addInstruction()).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DecacCompiler {
    private static final Logger LOG = Logger.getLogger(DecacCompiler.class);

    /**
     * Portable newline character.
     */
    private static final String nl = System.getProperty("line.separator", "\n");

    public DecacCompiler(CompilerOptions compilerOptions, File source) {
        super();
        this.compilerOptions = compilerOptions;
        this.source = source;
        this.symbolTable = new SymbolTable();
        this.envTypesPredef = new EnvironmentType(this);
    }

    /**
     * Source file associated with this compiler instance.
     */
    public File getSource() {
        return source;
    }

    /**
     * Compilation options (e.g. when to stop compilation, number of registers
     * to use, ...).
     */
    public CompilerOptions getCompilerOptions() {
        return compilerOptions;
    }


    /**
     * @see fr.ensimag.deca.tools.SymbolTable#create(String)
     */
    public SymbolTable.Symbol createSymbol(String name) {
        return symbolTable.create(name);
    }

    private final CompilerOptions compilerOptions;
    private final File source;
    private final SymbolTable symbolTable;
    private final EnvironmentType envTypesPredef;

    /**
     * Run the compiler (parse source file, generate code)
     *
     * @return true on error
     */
    public boolean compile() {
        String sourceFile = source.getAbsolutePath();
        String fileExtension = compilerOptions.getGenerateARMAssembly() ? ".s" : ".ass";
        String destFile = sourceFile.split("\\.deca")[0] + fileExtension;
        // A FAIRE: calculer le nom du fichier .ass à partir du nom du
        // A FAIRE: fichier .deca.
        PrintStream err = System.err;
        PrintStream out = System.out;
        LOG.debug("Compiling file " + sourceFile + " to assembly file " + destFile);
        try {
            return doCompile(sourceFile, destFile, out, err);
        } catch (LocationException e) {
            e.display(err);
            return true;
        } catch (DecacFatalError e) {
            err.println(e.getMessage());
            return true;
        } catch (StackOverflowError e) {
            LOG.debug("stack overflow", e);
            err.println("Stack overflow while compiling file " + sourceFile + ".");
            return true;
        } catch (Exception e) {
            LOG.fatal("Exception raised while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        } catch (AssertionError e) {
            LOG.fatal("Assertion failed while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        }
    }

    /**
     * Internal function that does the job of compiling (i.e. calling lexer,
     * verification and code generation).
     *
     * @param sourceName name of the source (deca) file
     * @param destName   name of the destination (assembly) file
     * @param out        stream to use for standard output (output of decac -p)
     * @param err        stream to use to display compilation errors
     * @return true on error
     */
    private boolean doCompile(String sourceName, String destName,
                              PrintStream out, PrintStream err)
            throws DecacFatalError, LocationException {
        AbstractProgram abstractProgram = doLexingAndParsing(sourceName, err);

        if (abstractProgram == null) {
            LOG.info("Parsing failed");
            return true;
        }
        assert (abstractProgram.checkAllLocations());

        if (compilerOptions.getParseThenStop()) {
            abstractProgram.decompile(System.out);
            System.exit(0);
        }

        abstractProgram.verifyProgram(this);
        assert (abstractProgram.checkAllDecorations());

        if (compilerOptions.getVerifyThenStop()) {
            System.exit(0);
        }

        /**
         * The main program. Every instruction generated will eventually end up here.
         */
        OutputProgram program;
        if (compilerOptions.getGenerateARMAssembly()) {
            program = new ARMProgram();
            abstractProgram.codeGen((ARMProgram) program);
        } else {
            program = new IMAProgram(compilerOptions.getNumberOfRegisters(), compilerOptions.getShouldCheck());
            ((IMAProgram) program).setMaxRegister(compilerOptions.getNumberOfRegisters() - 1);
            abstractProgram.codeGen((IMAProgram) program);
        }

        LOG.debug("Generated assembly code:" + nl + program.display());
        LOG.info("Output file assembly file is: " + destName);

        FileOutputStream fstream = null;
        try {
            fstream = new FileOutputStream(destName);
        } catch (FileNotFoundException e) {
            throw new DecacFatalError("Failed to open output file: " + e.getLocalizedMessage());
        }

        LOG.info("Writing assembler file ...");

        program.display(new PrintStream(fstream));
        LOG.info("Compilation of " + sourceName + " successful.");
        return false;
    }

    /**
     * Build and call the lexer and parser to build the primitive abstract
     * syntax tree.
     *
     * @param sourceName Name of the file to parse
     * @param err        Stream to send error messages to
     * @return the abstract syntax tree
     * @throws DecacFatalError    When an error prevented opening the source file
     * @throws DecacInternalError When an inconsistency was detected in the
     *                            compiler.
     * @throws LocationException  When a compilation error (incorrect program)
     *                            occurs.
     */
    protected AbstractProgram doLexingAndParsing(String sourceName, PrintStream err)
            throws DecacFatalError, DecacInternalError {
        DecaLexer lex;
        try {
            lex = new DecaLexer(CharStreams.fromFileName(sourceName));
        } catch (IOException ex) {
            throw new DecacFatalError("Failed to open input file: " + ex.getLocalizedMessage());
        }
        lex.setDecacCompiler(this);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        DecaParser parser = new DecaParser(tokens);
        parser.setDecacCompiler(this);
        return parser.parseProgramAndManageErrors(err);
    }

    public TypeDefinition getTypeDefinition(SymbolTable.Symbol typeSymbol) {
        return envTypesPredef.get(typeSymbol);
    }

    public void declareTypeDefinition(SymbolTable.Symbol typeSymbol, TypeDefinition definition)
            throws EnvironmentType.DoubleDefException {
        envTypesPredef.declare(typeSymbol, definition);
    }

    public TypeDefinition getTypeDefinition(String typeSymbol) {
        return getTypeDefinition(symbolTable.create(typeSymbol));
    }

    public Type getType(String typeSymbol) {
        return getTypeDefinition(typeSymbol).getType();
    }

}
