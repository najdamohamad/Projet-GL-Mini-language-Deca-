package fr.ensimag.deca.tree;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.instructions.DIV;
import fr.ensimag.ima.pseudocode.instructions.QUO;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.Validate;
import fr.ensimag.deca.context.FloatType;

/**
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Divide extends AbstractOpArith {
    public Divide(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    //@Override
    public void codeGenExpr(IMAProgram program,DVal value,GPRegister register) {
        super.codeGen(program);
        if (this.getLeftOperand().isFloat()){
            //division floattants
            program.addInstruction(new DIV(value, register));
        }
        else{
            //division entier
            program.addInstruction(new QUO(value, register));
        }
    }
    @Override
    protected String getOperatorName() {
        return "/";
    }

}
