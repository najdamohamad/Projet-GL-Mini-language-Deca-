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
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        if (leftExprType.isBoolean() && rightExprType.isBoolean()) {
            Type exprType = compiler.getType("boolean");
            setType(exprType);
            return exprType;
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression booléenne `" + this.decompile()
                    + "`, attendu `boolean`";
            throw new ContextualError(message, getLocation());
        }
    }

}
