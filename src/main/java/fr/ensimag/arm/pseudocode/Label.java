package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Label implements Operand {
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    /**
     * A line in an ARM Program is just a value (register or otherwise) you can print in text.
     *
     * @param s The print stream that stores in constructed program.
     */
    @Override
    public void display(PrintStream s) {
        s.print(name);
    }
}
