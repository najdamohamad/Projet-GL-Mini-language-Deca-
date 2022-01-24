package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Exit;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentType;
import fr.ensimag.deca.context.ObjectType;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * Deca complete program (class definition plus main block)
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Program extends AbstractProgram {
    private static final Logger LOG = Logger.getLogger(Program.class);

    public Program(ListDeclClass classes, AbstractMain main) {
        Validate.notNull(classes);
        Validate.notNull(main);
        this.classes = classes;
        this.main = main;
    }

    public ListDeclClass getClasses() {
        return classes;
    }

    public AbstractMain getMain() {
        return main;
    }

    private ListDeclClass classes;
    private AbstractMain main;

    @Override
    public void verifyProgram(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify program: start");

        SymbolTable.Symbol objectSymbol = compiler.createSymbol("Object");
        ClassType objectType = new ObjectType(
                objectSymbol,
                getLocation()
        );
        try {
            compiler.declareTypeDefinition(objectSymbol, objectType.getDefinition());
        } catch (EnvironmentType.DoubleDefException e) {
            throw new DecacInternalError("unreachable.");
        }

        LOG.debug("verify classes, pass 1: start");
        classes.verifyListClass(compiler);
        LOG.debug("verify classes, pass 1: start");

        LOG.debug("verify classes, pass 2: start");
        classes.verifyListClassMembers(compiler);
        LOG.debug("verify classes, pass 2: start");

        LOG.debug("verify classes, pass 3: start");
        classes.verifyListClassBody(compiler);
        LOG.debug("verify classes, pass 3: start");

        LOG.debug("verify main: start");
        main.verifyMain(compiler);
        LOG.debug("verify main: start");

        LOG.debug("verify program: end");
    }

    // Error labels.
    public static final Label ARITHMETIC_OVERFLOW_ERROR = new Label("ArithmeticOverflowError");
    public static final Label STACK_OVERFLOW_ERROR = new Label("StackOverflowError");
    public static final Label DIVISION_BY_ZERO_ERROR = new Label("DivisionByZeroError");
    public static final Label IO_ERROR = new Label("IOError");
    public static final Label NULL_DEREF_ERROR = new Label("NullDerefError");

    /**
     * CodeGen for main programs.
     * Follows the code listing p209,
     * 1 Génération de code pour le langage Deca « sans objet ».
     *
     * @param program Abstract representation of the IMA assembly code.
     */
    @Override
    public int codeGen(IMAProgram program) {
        program.addComment("------------------------------------------------");
        program.addComment("Method table construction");
        program.addComment("------------------------------------------------");
        int classesUsage = classes.codeGenMethodTable(program);
        LOG.trace("classes stack usage" + classesUsage);
        program.addComment("------------------------------------------------");
        program.addComment("Main program");
        program.addComment("------------------------------------------------");
        int stackUsage = main.codeGen(program);

        program.addInstruction(new HALT());

        program.addComment("------------------------------------------------");
        program.addComment("Classes method & constructors");
        program.addComment("------------------------------------------------");
        classes.codeGen(program);

        program.addComment("------------------------------------------------");
        program.addComment("Error handlers");
        program.addComment("------------------------------------------------");
        // -r option specifies that only 11.1 and 11.3 should be ignored,
        // NOT 11.2 which are the IO errors.
        program.addLabel(IO_ERROR);
        program.addInstruction(new WSTR("Erreur : valeur entrée pas dans le type attendu."));
        program.addInstruction(new WNL());
        program.addInstruction(new ERROR());

        if (program.shouldCheck()) {
            program.addFirst(new BOV(STACK_OVERFLOW_ERROR));
            program.addFirst(new TSTO(new ImmediateInteger(classesUsage + stackUsage)));

            program.addLabel(ARITHMETIC_OVERFLOW_ERROR);
            program.addInstruction(new WSTR("Erreur : débordement pendant opération arithmétique entre deux flottants."));
            program.addInstruction(new WNL());
            program.addInstruction(new ERROR());
            program.addLabel(DIVISION_BY_ZERO_ERROR);
            program.addInstruction(new WSTR("Erreur : division par zéro."));
            program.addInstruction(new WNL());
            program.addInstruction(new ERROR());
            program.addLabel(STACK_OVERFLOW_ERROR);
            program.addInstruction(new WSTR("Erreur : débordement de la pile."));
            program.addInstruction(new WNL());
            program.addInstruction(new ERROR());
            program.addLabel(NULL_DEREF_ERROR);
            program.addInstruction(new WSTR("Erreur : déréférencement d'un pointer `null`"));
            program.addInstruction(new WNL());
            program.addInstruction(new ERROR());
        }
        program.addLabel(new Label("code.Object.equals"));;
        program.addInstruction(new WSTR("DecacError: not implemented yet"));
        program.addInstruction(new WNL());
        program.addInstruction(new ERROR());
        return 0;
    }

    @Override
    public void codeGen(ARMProgram program) {
        Line programEntryGlobal = new Directive("global", "_start");
        program.addLineInSection("text", programEntryGlobal);
        Line programEntry = new LabelDefinition("_start");
        program.addLineInSection("text", programEntry);
        Line newlineLabel = new LabelDefinition("newline");
        Line newlineByte = new Directive("byte", "0xA");
        program.addLineInSection("data", newlineLabel);
        program.addLineInSection("data", newlineByte);
        main.codeGen(program);
        Line exitProgram = new Exit(new Immediate(0)); // status = 0
        program.addLineInSection("text", exitProgram);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        getClasses().decompile(s);
        getMain().decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        classes.iter(f);
        main.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        classes.prettyPrint(s, prefix, false);
        main.prettyPrint(s, prefix, true);
    }
}
