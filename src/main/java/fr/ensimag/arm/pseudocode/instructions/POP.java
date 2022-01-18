package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.UnaryInstructionMultiple;

public class POP extends UnaryInstructionMultiple {
    public POP(Operand... operands) {
        super(operands);
    }

    @Override
    public String getName() {
        return "pop";
    }
}
