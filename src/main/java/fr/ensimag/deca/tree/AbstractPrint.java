package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Label;
import java.io.PrintStream;
import java.util.Iterator;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

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
            // TODO: For hello world language, we accept only strings.
            // Need to add a rule to accept float and int.
            if (!exprType.sameType(new StringType(null))) {
                LOG.error("Mauvais type pour print: s'attendait a string ou float ou int, a obtenu "
                        + exprType);
                throw new ContextualError(
                        "Mauvais type pour print: s'attendait a string ou float ou int, a obtenu "
                                + exprType, getLocation());
            }
        }
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenPrint(compiler);
        }
    }

    private boolean getPrintHex() {
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
