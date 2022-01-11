package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Assign implements Line {
    private final String bindingName;
    private final Operand operand;

    public Assign(String bindingName, Operand operand) {
        this.bindingName = bindingName;
        this.operand = operand;
    }

    /**
     * A line in an ARM OutputProgram is just a thing you can print in text.
     *
     * @param s The print stream that stores in constructed program.
     */
    @Override
    public void display(PrintStream s) {
        s.print(bindingName + " = ");
        operand.display(s);
        s.println();
    }
}
