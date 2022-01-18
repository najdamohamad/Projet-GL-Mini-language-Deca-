package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.UnaryInstructionMultiple;

public class PUSH extends UnaryInstructionMultiple {
    public PUSH(Operand... operands) {
        super(operands);
    }

    @Override
    public String getName() {
        return "push";
    }
}
