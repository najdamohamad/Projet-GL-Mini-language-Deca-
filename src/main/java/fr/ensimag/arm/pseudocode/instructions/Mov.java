package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.BinaryInstructionRegToAny;
import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.Register;

public class Mov extends BinaryInstructionRegToAny {

    public Mov(Register register, Operand operand) {
        super(register, operand);
    }

    @Override
    public String getName() {
        return "mov";
    }
}
