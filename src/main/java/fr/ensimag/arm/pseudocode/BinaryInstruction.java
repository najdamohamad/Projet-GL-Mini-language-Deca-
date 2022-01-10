package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public abstract class BinaryInstruction implements Instruction {
    private final Operand operand1;
    private final Operand operand2;

    protected BinaryInstruction(Operand operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        s.print(operand1);
        s.print(", ");
        s.print(operand2);
    }

    @Override
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
        s.println();
    }
}
