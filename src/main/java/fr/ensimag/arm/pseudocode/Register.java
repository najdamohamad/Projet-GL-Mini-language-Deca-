package fr.ensimag.arm.pseudocode;

import org.apache.commons.lang.Validate;

import java.io.PrintStream;

public class Register implements Operand {

    /**
     * AArch32 includes 13 general registers, R0-12, the OutputProgram Counter, R15,
     * and two banked registers that contain the Stack Pointer, R13, and Link Register, R14.
     */
    private final int number;

    public Register(int number) {
        Validate.isTrue(number >= 0 && number < 15);
        this.number = number;
    }

    @Override
    public void display(PrintStream s) {
        s.print("r" + number);
    }
}
