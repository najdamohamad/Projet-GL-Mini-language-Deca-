package fr.ensimag.arm.pseudocode;

public abstract class BinaryInstructionRegToAny extends BinaryInstruction {

    public BinaryInstructionRegToAny(Register register, Operand operand2) {
        super(register, operand2);
    }
}
