package fr.ensimag.arm.pseudocode.instructions;

import fr.ensimag.arm.pseudocode.*;

import java.io.PrintStream;

/**
 * https://stackoverflow.com/questions/37840754/what-does-an-equals-sign-on-the-right-side-of-a-ldr-instruction-in-arm-mean
 */
public class LDRPseudoInstruction extends BinaryInstructionRegToAny {

    @Override
    public String getName() {
        return "ldr";
    }

    public LDRPseudoInstruction(Register register, Label labelExpr) {
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
