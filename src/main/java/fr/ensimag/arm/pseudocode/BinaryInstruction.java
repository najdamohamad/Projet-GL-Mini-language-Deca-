package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public abstract class BinaryInstruction implements Instruction {
    protected final Operand operand1;
    protected final Operand operand2;

    protected BinaryInstruction(Operand operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        operand1.display(s);
        s.print(", ");
        operand2.display(s);
    }

    @Override
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
        s.println();
    }
}
