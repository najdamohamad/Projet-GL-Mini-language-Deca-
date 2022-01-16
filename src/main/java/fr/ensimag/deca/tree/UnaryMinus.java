package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.instructions.MUL;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class UnaryMinus extends AbstractUnaryExpr {

    public UnaryMinus(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type operandType = getOperand().verifyExpr(compiler, localEnv, currentClass);
        if (!operandType.isIntOrFloat()) {
            String message = "TypeError: type incorrect dans `"
                    + "l'expression de d'inversion de signe `" + this.decompile()
                    + "`, attendu `int` ou bien `float` mais trouvé `"
                    + operandType + "`.";
            throw new ContextualError(message, getLocation());
        }
        setType(operandType);
        return operandType;
    }


    @Override
    protected String getOperatorName() {
        return "-";
    }

    @Override
    public void codeGen(IMAProgram program) {
        getOperand().codeGen(program);
        program.addInstruction(new MUL(new ImmediateInteger(-1), program.getMaxUsedRegister()));
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
