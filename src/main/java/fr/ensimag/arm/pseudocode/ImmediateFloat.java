package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class ImmediateFloat implements Operand {

    private final float value;

    public ImmediateFloat(float value) {
        this.value = value;
    }

    @Override
    public void display(PrintStream s) {
        s.print("#0x" + Integer.toHexString(Float.floatToRawIntBits(value)));
    }
}
