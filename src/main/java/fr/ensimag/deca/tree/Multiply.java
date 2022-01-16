package fr.ensimag.deca.tree;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.MUL;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.Validate;


/**
 * @author gl47
 * @date 01/01/2022
 */
public class Multiply extends AbstractOpArith {
    public Multiply(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        program.addInstruction(new MUL(dval, reg));
        if (isFloat()) {
            program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
                    "adding two floats, overflow check for MUL");
        }
    }

    @Override
    protected String getOperatorName() {
        return "*";
    }

}
