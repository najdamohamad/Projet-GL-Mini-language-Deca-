package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.util.Iterator;

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
        for (Iterator<AbstractExpr> it = arguments.iterator(); it.hasNext(); ) {
            AbstractExpr expr = it.next();
            Type exprType = expr.verifyExpr(compiler, localEnv, currentClass);
            boolean printable = exprType.sameType(new StringType(null)) ||
                    exprType.sameType(new FloatType(null)) ||
                    exprType.sameType(new IntType(null));
            if (!printable) {
                LOG.error("Mauvais type pour print: s'attendait a string ou float ou int, a obtenu "
                        + exprType);
                throw new ContextualError(
                        "TypeError: s'attendait a string ou float ou int, a obtenu "
                                + exprType, getLocation());
            }
        }
    }

    @Override
    public void codeGen(IMAProgram program) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenDisplay(program);
        }
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
