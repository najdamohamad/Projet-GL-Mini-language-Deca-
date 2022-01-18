package fr.ensimag.arm.pseudocode;

import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.GPRegister;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;
import java.util.Optional;

public class Register implements Operand {
    /**
     * https://azeria-labs.com/assembly-basics-cheatsheet/
     * AArch32 includes 13 general registers, R0-12, the OutputProgram Counter, R15,
     * and two banked registers that contain the Stack Pointer, R13, and Link Register, R14.
     *
     * 11 registers are general-purpose:
     * - R0 to R6
     * - R8 to R10
     * We skip R7 which is used for syscalls.
     * This leaves us with 10 registers.
     */
    private final int number;
    /**
     * A register may have a name directly instead of a number.
     */
    private final String name;

    private Register(String name) {
        this.number = 0;
        this.name = name;
    }

    private Register(int number) {
        Validate.isTrue(number >= 0 && number < 15);
        this.number = number;
        this.name = null;
    }

    @Override
    public void display(PrintStream s) {
        if (name != null) {
            s.print(name);
        } else {
            s.print("r" + number);
        }
    }

    /**
     * Frame Pointer
     */
    public static final Register FP = new Register("fp");
    /**
     * Intra Procedural
     */
    public static final Register IP = new Register("ip");
    /**
     * Stack Pointer
     */
    public static final Register SP = new Register("sp");
    /**
     * Link Register
     */
    public static final Register LR = new Register("lr");
    /**
     * Program Counter
     */
    public static final Register PC = new Register("pc");
    /**
     * Convenience r0
     */
    public static final Register R0 = new Register(0);
    /**
     * Convenience r1
     */
    public static final Register R1 = new Register(1);
    /**
     * Syscall register: r7
     */
    public static final Register R7 = new Register(7);
    /**
     * General Purpose Registers. Array is private because Java arrays cannot be
     * made immutable, use getR(i) to access it.
     */
    private static final Register[] R = initGPRegisters();

    /**
     * Get a general purpose register.
     * eg. getR(8) returns r8.
     */
    public static Register getR(int i) {
        Validate.isTrue(i != 7);
        return R[i];
    }

    static private Register[] initGPRegisters() {
        Register[] res = new Register[11];
        // R0 to R6
        for (int i = 0; i <= 6; i++) {
            res[i] = new Register(i);
        }
        // R8 to R10
        for (int i = 8; i <= 10; i++) {
            res[i] = new Register(i);
        }
        // res[7] is left intentionnaly empty.
        return res;
    }
}
