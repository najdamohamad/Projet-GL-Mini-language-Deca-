package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.BinaryInstructionRegToAny;
import fr.ensimag.arm.pseudocode.Label;
import fr.ensimag.arm.pseudocode.Register;

import java.io.PrintStream;

/**
 * Normal LDR
 */
public class LDR extends BinaryInstructionRegToAny {

    @Override
    public String getName() {
        return "ldr";
    }

    public LDR(Register register, Label labelExpr) {
        super(register, labelExpr);
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        operand1.display(s);
        // Note the equal sign here, this is a pseudo-instruction.
        s.print(", =");
        operand2.display(s);
    }
}
