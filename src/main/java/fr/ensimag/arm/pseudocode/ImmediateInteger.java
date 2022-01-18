package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class ImmediateInteger extends Immediate {

    private final int value;

    public ImmediateInteger(int value) {
        this.value = value;
    }

    @Override
    public void display(PrintStream s) {
        s.print("#" + value);
    }
}
