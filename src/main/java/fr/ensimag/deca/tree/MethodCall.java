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

import java.io.PrintStream;

/**
 * Method
 *
 * @author gl47
 * @date 01/01/2022
 */
public class MethodCall extends AbstractExpr {

    private AbstractExpr expression;
    private AbstractIdentifier method;
    private ListExpr listParams;

    public MethodCall(AbstractExpr expression, AbstractIdentifier method, ListExpr listParams) {
        Validate.notNull(expression);
        Validate.notNull(method);
        Validate.notNull(listParams);
        this.expression = expression;
        this.method = method;
        this.listParams = listParams;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("Not yet supported");
    }


    @Override
    public void decompile(IndentPrintStream s) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("Not yet supported");
    }
}