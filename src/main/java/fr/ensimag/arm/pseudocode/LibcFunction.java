package fr.ensimag.arm.pseudocode;

import fr.ensimag.arm.pseudocode.instructions.BL;
import fr.ensimag.arm.pseudocode.instructions.MOV;

import java.io.PrintStream;
import java.util.stream.IntStream;

public abstract class LibcFunction implements Line {
    /**
     * Operands to this function call.
     * TODO: In the ARM EABI (calling convention), only R0-R3 are function arguments:
     * the rest are passed on the stack.
     * https://gitlab.ensimag.fr/gl2022/g9/gl47/-/wikis/Mise-en-place-de-ARM#calling-convention
     */
    private final Operand[] operands;

    public LibcFunction(Operand[] operands) {
        this.operands = operands;
    }

    /**
     * A description of this libc function: function name, arguments,
     * like a C signature.
     */
    public abstract String getDescription();

    /**
     * The name of the function, which is also the symbol name.
     */
    public abstract String getSymbol();

    private Line functionArgument(int i) {
        return new MOV(Register.getR(i), operands[i]);
    }

    @Override
    public void display(PrintStream s) {
        Line description = new Comment(getDescription());
        description.display(s);
        IntStream
                .range(0, operands.length)
                .forEachOrdered(i -> functionArgument(i).display(s));
        BL branchToFunction = new BL(new Label(getSymbol()));
        branchToFunction.display(s);
    }
}
