package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.*;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Divide extends AbstractOpArith {
    public Divide(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        if (getType().isFloat()) {
            program.addInstruction(new DIV(dval, reg));
            if (program.shouldCheck()) {
                // Division may have overflowed
                program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
                        "two floats, overflow check for DIV");
            }
        } else {
            program.addInstruction(new QUO(dval, reg));
            if (program.shouldCheck()) {
                // Check for divide by 0
                // p.108: QUO sets CP flags
                // in other words, OV flag if set if QUO with 0 as second operand
                program.addInstruction(new BOV(Program.DIVISION_BY_ZERO_ERROR), "may have tried to div by 0");
            }
        }
    }

    @Override
    protected String getOperatorName() {
        return "/";
    }

}
