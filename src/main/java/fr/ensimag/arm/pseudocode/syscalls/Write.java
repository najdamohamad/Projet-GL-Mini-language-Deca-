package fr.ensimag.arm.pseudocode.syscalls;

import fr.ensimag.arm.pseudocode.Syscall;

public class Write extends Syscall {
    @Override
    public String getDescription() {
        return "exit(int status)";
    }

    public Write() {
    }
}
