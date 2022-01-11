package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public interface Instruction extends Line {
    String getName();
    void displayOperands(PrintStream s);
}
