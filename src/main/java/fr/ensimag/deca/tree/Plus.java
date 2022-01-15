package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.ADD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;

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
        GPRegister freeRegister = program.getFreeRegister();
        if (freeRegister.getNumber() == program.getMaxRegister()) {
            getLeftOperand().codeGen(program);
            program.addComment("Save the register, reached MAX");
            program.addInstruction(new PUSH(freeRegister));
            getRightOperand().codeGen(program);
            // TODO: the slices for étape C say add this instrution,
            //       but in our case the result is already i R0.
            // program.addInstruction(new LOAD(freeRegister, Register.R0));
            program.addInstruction(new POP(freeRegister));
            program.addComment("Restore the register");
        } else {
            getLeftOperand().codeGen(program);
            GPRegister nextRegister = program.bumpFreeRegister();
            getRightOperand().codeGen(program);
            program.addInstruction(new ADD(
                    freeRegister,
                    nextRegister
            ));
        }
    }

    @Override
    protected String getOperatorName() {
        return "+";
    }
}
