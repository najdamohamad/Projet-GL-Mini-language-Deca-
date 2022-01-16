package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.REM;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Not extends AbstractUnaryExpr {

    public Not(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type operandType = getOperand().verifyExpr(compiler, localEnv, currentClass);
        if (!operandType.isBoolean()) {
            String message = "TypeError: type incorrect dans `"
                    + "l'expression de négation `" + this.decompile()
                    + "`, attendu `boolean` mais trouvé `" + operandType + "`.";
            throw new ContextualError(message, getLocation());
        }
        Type exprType = compiler.getType("boolean");
        setType(exprType);
        return exprType;
    }


    @Override
    protected String getOperatorName() {
        return "!";
    }

    @Override
    public void codeGen(IMAProgram program) {
        getOperand().codeGen(program);
        // not x = (x + 1) % 2
        program.addInstruction(new ADD(new ImmediateInteger(1), Register.R0));
        program.addInstruction(new REM(new ImmediateInteger(2), Register.R0));
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
