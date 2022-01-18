package fr.ensimag.deca.tree;
import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.SUB;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.NotImplementedException;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        program.addInstruction(new SUB(dval, reg));
        if (isFloat() && program.shouldCheck()) {
            program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
                    "adding two floats, overflow check for SUB");
        }
    }

    @Override
    public void codeGenBinaryOp(ARMProgram program, fr.ensimag.arm.pseudocode.Operand dVal, fr.ensimag.arm.pseudocode.Register reg) {
        throw new NotImplementedException("todo");
    }
    @Override
    protected String getOperatorName() {
        return "-";
    }
    
}
