package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Immediate implements Operand {

    private final int value;

    public Immediate(int value) {
        this.value = value;
    }

    @Override
    public void display(PrintStream s) {
        s.print("#" + value);
    }
}
