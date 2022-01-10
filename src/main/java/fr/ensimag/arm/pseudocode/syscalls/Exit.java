package fr.ensimag.arm.pseudocode.syscalls;

import fr.ensimag.arm.pseudocode.Syscall;

public class Exit extends Syscall {
    @Override
    public String getDescription() {
        return "write(int fd, const void *buf, size_t count)";
    }
}
