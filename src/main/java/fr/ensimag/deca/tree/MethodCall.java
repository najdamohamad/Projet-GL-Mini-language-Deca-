package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
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
    private ListExpr listArgs;

    public MethodCall(AbstractExpr expression, AbstractIdentifier method, ListExpr listArgs) {
        Validate.notNull(expression);
        Validate.notNull(method);
        Validate.notNull(listArgs);
        this.expression =expression;
        this.method = method;
        this.listArgs = listArgs;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        String message = "TypeError: " + expression.decompile() + "` n'est pas un objet.";
        ClassType exprType = expression
                .verifyExpr(compiler, localEnv, currentClass)
                .asClassType(message, getLocation());
        message = "TypeError: `" + method.decompile() + "` n'est pas une méthode.";
        MethodDefinition definition = currentClass
                .getMembers()
                .get(method.getName())
                .asMethodDefinition(message, getLocation());
        ClassType classType = currentClass.getType();
        for (int i = 0; i < listArgs.size(); i++) {
            Type paramType = definition.getSignature().paramNumber(i);
            // TODO: verify that we should do ConvFloat in method arguments.
            AbstractExpr rvalueArg = listArgs
                    .getList()
                    .get(i)
                    .verifyRValue(compiler, localEnv, currentClass, paramType);
            listArgs.set(i, rvalueArg);
        }
        return exprType;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print(".");
        method.decompile(s);
        s.print("(");
        listArgs.decompile(s);
        s.print(")");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iterChildren(f);
        method.iterChildren(f);
        listArgs.iterChildren(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, false);
        method.prettyPrint(s, prefix, false);
        listArgs.prettyPrint(s, prefix, false);
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    public int codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("Not yet supported");
    }
}