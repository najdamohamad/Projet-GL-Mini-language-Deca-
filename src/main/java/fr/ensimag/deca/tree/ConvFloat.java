package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;

/**
 * Conversion of an int into a float. Used for implicit conversions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ConvFloat extends AbstractUnaryExpr {
    public ConvFloat(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type typeExpr = getOperand().verifyExpr(compiler, localEnv, currentClass);

        // This is an internal compiler error if the ConvFLoat does not convert an int.
        // This node does not occur naturally in the tree, our compiler does the conversion naturally,
        // so any error is our own.
        assert (typeExpr.isInt());
        return compiler.getType("float");
    }


    @Override
    protected String getOperatorName() {
        return "/* conv float */";
    }

}
