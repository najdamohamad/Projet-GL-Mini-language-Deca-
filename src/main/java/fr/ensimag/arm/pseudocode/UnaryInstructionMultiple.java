package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;
import java.util.List;

public abstract class UnaryInstructionMultiple implements Instruction {
    protected final List<Operand> operands;

    protected UnaryInstructionMultiple(Operand... operands) {
        this.operands = List.of(operands);
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" {");

        String delim = "";
        for (Operand o: operands) {
            s.print(delim);
            o.display(s);
            delim = ", ";
        }
        s.print("}");
    }

    @Override
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
        s.println();
    }
}
