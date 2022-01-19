package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.UnaryInstruction;

/**
 * Branch and link.
 * Used for subroutine calls, eg. printf.
 */
public class BL extends UnaryInstruction {
    public BL(Operand operand) {
        super(operand);
    }

    @Override
    public String getName() {
        return "bl";
    }
}
