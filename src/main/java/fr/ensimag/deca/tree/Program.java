package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Exit;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
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
        main.verifyMain(compiler);
        LOG.debug("verify program: end");
    }

    // Error labels.
    public static final Label ARITHMETIC_OVERFLOW_ERROR = new Label("ArithmeticOverflowError");
    public static final Label STACK_OVERFLOW_ERROR = new Label("StackOverflowError");
    public static final Label DIVISION_BY_ZERO_ERROR = new Label("DivisionByZeroError");

    /**
     * CodeGen for main programs.
     * Follows the code listing p209,
     * 1 Génération de code pour le langage Deca « sans objet ».
     * @param program Abstract representation of the IMA assembly code.
     */
    @Override
    public void codeGen(IMAProgram program) {
        program.addComment("Main program");
        // TODO: test de dépassement de pile doit être fait à la fin du programme
        // Utiliser les possibilités du paquet pseudocode, voir p210
        main.codeGen(program);

        program.addInstruction(new HALT());
        program.addComment("End of main function.");

        program.addFirst(new BOV(STACK_OVERFLOW_ERROR));
        program.addFirst(new TSTO(new ImmediateInteger(program.getStackUsage())));

        program.addLabel(ARITHMETIC_OVERFLOW_ERROR);
        program.addInstruction(new WSTR("Erreur : débordement pendant opération arithmétique entre deux flottants."));
        program.addInstruction(new WNL());
        program.addInstruction(new ERROR());
        program.addLabel(DIVISION_BY_ZERO_ERROR);
        program.addInstruction(new WSTR("Erreur : divison par zéro."));
        program.addInstruction(new WNL());
        program.addInstruction(new ERROR());
        program.addLabel(STACK_OVERFLOW_ERROR);
        program.addInstruction(new WSTR("Erreur : débordement de la pile."));
        program.addInstruction(new WNL());
        program.addInstruction(new ERROR());
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
