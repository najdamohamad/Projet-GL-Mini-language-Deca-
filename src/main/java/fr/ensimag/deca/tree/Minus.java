package fr.ensimag.deca.tree;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.SUB;
import fr.ensimag.ima.pseudocode.DVal;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg, boolean invertCondition) {
        program.addInstruction(new SUB(dval, reg));
        if (isFloat() && program.shouldCheck()) {
            program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
                    "adding two floats, overflow check for SUB");
        }
    }

    @Override
    protected String getOperatorName() {
        return "-";
    }
    
}
