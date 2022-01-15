package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Plus extends AbstractOpArith {
    public Plus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeOpe(IMAProgram program, DVal value, GPRegister register) {
        super.codeGen(program);
        program.addInstruction(new ADD(value, register));
    }

    @Override
    public void codeGen(IMAProgram program) {
        // Put the result of evaluating the LHS expression into R0, then
        // save it into R1 to make room for the RHS expression's value.
        getLeftOperand().codeGen(program);
        // TODO: check if using R1 is appropriate.
        program.addInstruction(new LOAD(Register.R0, Register.R1));
        getRightOperand().codeGen(program);
        // Put the sum of R0 and R1 into R0.
        program.addInstruction(new ADD(Register.R1, Register.R0));
    }

    @Override
    protected String getOperatorName() {
        return "+";
    }
}
