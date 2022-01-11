package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public abstract class UnaryInstruction implements Instruction {
    private final Operand operand;

    protected UnaryInstruction(Operand operand) {
        this.operand = operand;
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        operand.display(s);
    }

    @Override
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
        s.println();
    }
}
