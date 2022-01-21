package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class InstanceOf extends AbstractExpr {

    public AbstractExpr getExpression() {
        return expression;
    }


    public AbstractIdentifier getTypeInstanced() {
        return typeInstanced;
    }

    protected void setTypeInstanced(AbstractIdentifier typeInstanced) {
        Validate.notNull(typeInstanced);
        this.typeInstanced = typeInstanced;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type exprType = expression.verifyExpr(compiler, localEnv, currentClass);
        Type idType = typeInstanced.verifyType(compiler);
        String message = "TypeError: " + expression.decompile() + "isn't an object or null";
        if(!exprType.isClassOrNull()){
            throw new ContextualError(message, getLocation());
        }
        else if(!idType.isClass()){
            message = "TypeError: " + typeInstanced.decompile() + "isn't a class";
            throw new ContextualError(message, getLocation());
        }
        Type returnType = compiler.getType("boolean");
        setType(returnType);
        return returnType;
    }

    protected void setExpression(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    private AbstractExpr expression;
    private AbstractIdentifier typeInstanced;

    public InstanceOf (AbstractExpr expression,
                AbstractIdentifier typeInstanced) {
        Validate.notNull(expression, "Type casted to cannot be null");
        Validate.notNull(typeInstanced, "right operand cannot be null");
        Validate.isTrue(expression != typeInstanced, "Sharing subtrees is forbidden");
        this.expression = expression;
        this.typeInstanced = typeInstanced;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print("instanceOf(");
        typeInstanced.decompile(s);
        s.print(")");
    }

    protected String getOperatorName() {
        return "(";
    }

    ;

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
        typeInstanced.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, false);
        typeInstanced.prettyPrint(s, prefix, true);
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

