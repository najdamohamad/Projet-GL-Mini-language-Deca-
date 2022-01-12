package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;

/**
 * Arithmetic binary operations (+, -, /, ...)
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {

    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        Type exprType;
        if (leftExprType.isInt() && leftExprType.isInt()) {
            exprType = compiler.getType("int");
        } else if (leftExprType.isFloat() || rightExprType.isFloat()) {
            exprType = compiler.getType("float");
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression arithmétique `" + this.decompile()
                    + "`, attendu `float` ou bien `int`";
            throw new ContextualError(message, getLocation());
        }
        setType(exprType);
        return exprType;
    }
}
