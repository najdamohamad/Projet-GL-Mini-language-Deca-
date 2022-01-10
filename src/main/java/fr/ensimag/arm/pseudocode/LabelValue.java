package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class LabelValue implements Operand {
    private final String labelName;

    public LabelValue(String labelName) {
        this.labelName = labelName;
    }

    /**
     * A line in an ARM Program is just a value (register or otherwise) you can print in text.
     *
     * @param s The print stream that stores in constructed program.
     */
    @Override
    public void display(PrintStream s) {
        s.println("=" + labelName);
    }
}
