package fr.ensimag.arm.pseudocode.syscalls;

import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.Syscall;

public class Exit extends Syscall {
    @Override
    public String getDescription() {
        return "exit(int status)";
    }

    @Override
    public int getNumber() {
        return 1;
    }

    public Exit(Operand status) {
        super(status);
    }
}
