package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.ADD;

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
    protected String getOperatorName() {
        return "+";
    }
}
