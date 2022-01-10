package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public interface Operand {

    /**
     * A line in an ARM OutputProgram is just a value (register or otherwise) you can print in text.
     *
     * @param s The print stream that stores in constructed program.
     */
    void display(PrintStream s);
}
