package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Subtraction implements Operand {

    private final Operand operand1;
    private final Operand operand2;

    public Subtraction(Operand operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * A line in an ARM Program is just a value (register or otherwise) you can print in text.
     *
     * @param s The print stream that stores in constructed program.
     */
    @Override
    public void display(PrintStream s) {
        operand1.display(s);
        s.print(" - ");
        operand2.display(s);
    }
}
