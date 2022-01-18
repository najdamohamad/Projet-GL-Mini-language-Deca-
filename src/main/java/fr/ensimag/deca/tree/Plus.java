package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import org.apache.commons.lang.NotImplementedException;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Plus extends AbstractOpArith {
    public Plus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        program.addInstruction(new ADD(dval, reg));
        if (isFloat() && program.shouldCheck()) {
            program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
            "adding two floats, overflow check for ADD");
        }
    }
    @Override
    public void codeGenBinaryOp(ARMProgram program, fr.ensimag.arm.pseudocode.Operand dVal, fr.ensimag.arm.pseudocode.Register reg) {
        throw new NotImplementedException("todo");
    }

    @Override
    protected String getOperatorName() {
        return "+";
    }
}
