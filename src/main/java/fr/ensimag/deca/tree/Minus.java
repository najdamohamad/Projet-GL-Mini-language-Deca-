package fr.ensimag.deca.tree;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.SUB;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.Validate;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeOpe(IMAProgram program,DVal value,GPRegister register) {
        super.codeGen(program);
        program.addInstruction(new SUB(value, register));
    }
    @Override
    protected String getOperatorName() {
        return "-";
    }
    
}
