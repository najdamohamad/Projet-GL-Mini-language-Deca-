package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;

/**
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpExactCmp extends AbstractOpCmp {

    public AbstractOpExactCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        Type exprType;
        boolean bothNumbers = (rightExprType.isInt() || rightExprType.isFloat())
                && (leftExprType.isInt() || leftExprType.isFloat());
        boolean bothObjects = ((rightExprType.isClassOrNull())
                && (leftExprType.isClassOrNull()));
        if (bothNumbers || bothObjects) {
            exprType = compiler.getType("boolean");
            setType(exprType);
            return exprType;
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression de comparaison `" + this.decompile()
                    + "`, seuls les types `int`/`float` ou bien les objets son comparables.";
            throw new ContextualError(message, getLocation());
        }
    }
}
