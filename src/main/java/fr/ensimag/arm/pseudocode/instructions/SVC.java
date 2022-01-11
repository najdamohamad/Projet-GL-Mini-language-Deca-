package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.Immediate;
import fr.ensimag.arm.pseudocode.UnaryInstruction;

public class SVC extends UnaryInstruction {

    @Override
    public String getName() {
        return "svc";
    }

    public SVC(Immediate immediate) {
        super(immediate);
    }
}
