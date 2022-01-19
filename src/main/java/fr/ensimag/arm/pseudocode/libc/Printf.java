package fr.ensimag.arm.pseudocode.libc;

import fr.ensimag.arm.pseudocode.LibcFunction;
import fr.ensimag.arm.pseudocode.Operand;

public class Printf extends LibcFunction {
    public Printf(Operand... operands) {
        super(operands);
    }

    @Override
    public String getDescription() {
        return "int printf(const char *format, ...)";
    }

    @Override
    public String getSymbol() {
        return "printf";
    }
}
