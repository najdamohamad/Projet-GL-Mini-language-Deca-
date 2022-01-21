package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * Print statement (print, println, ...).
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractPrint extends AbstractInst {
    private static final Logger LOG = Logger.getLogger(Program.class);

    private boolean printHex;
    private ListExpr arguments = new ListExpr();

    abstract String getSuffix();

    public AbstractPrint(boolean printHex, ListExpr arguments) {
        Validate.notNull(arguments);
        this.arguments = arguments;
        this.printHex = printHex;
    }

    public ListExpr getArguments() {
        return arguments;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
                              ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        for (AbstractExpr expr : getArguments().getList()) {
            Type exprType = expr.verifyExpr(compiler, localEnv, currentClass);
            boolean printable = exprType.sameType(compiler.getType("float")) ||
                    exprType.sameType(compiler.getType("int")) ||
                    exprType.sameType(compiler.getType("string"));
            if (!printable) {
                String message = "TypeError: type(s) incorrect(s) dans "
                        + "l'instruction `" + this.decompile()
                        + "`, attendu `int` ou bien `float`, mais trouvé `"
                        + exprType + "`.";
                throw new ContextualError(message, getLocation());
            }
        }
    }

    @Override
    public int codeGen(IMAProgram program) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenDisplay(program, printHex);
        }
        return 0; // No stack usage for print
    }

    @Override
    public void codeGen(ARMProgram program) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenDisplay(program);
        }
    }

    public boolean getPrintHex() {
        return printHex;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        arguments.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        arguments.prettyPrint(s, prefix, true);
    }

}
