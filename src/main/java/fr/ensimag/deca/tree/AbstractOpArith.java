package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;

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
            exprType = new IntType(null);
        } else if (leftExprType.isFloat() || rightExprType.isFloat()) {
            exprType = new FloatType(null);
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression arithmétique `" + this + "`, attendu "
                    + "`float` ou bien `int`";
            throw new ContextualError(message, getLocation());
        }
        setType(exprType);
        return exprType;
    }
}
