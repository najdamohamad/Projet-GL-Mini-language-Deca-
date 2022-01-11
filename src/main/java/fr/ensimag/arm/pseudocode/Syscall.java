package fr.ensimag.arm.pseudocode;

import fr.ensimag.arm.pseudocode.instructions.LDR;
import fr.ensimag.arm.pseudocode.instructions.MOV;
import fr.ensimag.arm.pseudocode.instructions.SVC;

import java.io.PrintStream;
import java.util.stream.IntStream;

public abstract class Syscall implements Line {
    public abstract String getDescription();

    public abstract int getNumber();

    private final Operand[] operands;

    public Syscall(Operand... operands) {
       this.operands = operands;
    }

    private Line syscallNumber() {
        return new MOV(new Register(7), new Immediate(getNumber()));
    }

    private Line syscallArgument(int i) {
        if (operands[i] instanceof Immediate) {
            return new MOV(new Register(i), operands[i]);
        } else {
            return new LDR(new Register(i), (Label)operands[i]);
        }
    }

    private Line superVisorCall() {
       return new SVC(new Immediate(0));
    }

    @Override
    public void display(PrintStream s) {
        Line description = new Comment(getDescription());
        description.display(s);
        syscallNumber().display(s);
        IntStream
            .range(0, operands.length)
            .forEachOrdered(i -> syscallArgument(i).display(s));
        superVisorCall().display(s);
    }
}
