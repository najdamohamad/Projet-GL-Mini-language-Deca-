package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;

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
        boolean bothNumbers = rightExprType.isIntOrFloat() && leftExprType.isIntOrFloat();
        boolean bothBooleans = rightExprType.isBoolean() && leftExprType.isBoolean();
        if (leftExprType.isInt() && rightExprType.isFloat()) {
            setLeftOperand(new ConvFloat(getLeftOperand()));
            getLeftOperand().setType(compiler.getType("float"));
        } else if (rightExprType.isInt() && leftExprType.isFloat()) {
            setRightOperand(new ConvFloat(getRightOperand()));
            getRightOperand().setType(compiler.getType("float"));
        } else if (!bothBooleans && !bothNumbers) {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression de comparaison `" + this.decompile()
                    + "`, seuls les types `int`/`float` ou bien les types `boolean` sont comparables.";
            throw new ContextualError(message, getLocation());
        }
        Type exprType = compiler.getType("boolean");
        setType(exprType);
        return exprType;

    }
}
