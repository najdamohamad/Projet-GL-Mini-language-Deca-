package fr.ensimag.arm.pseudocode.syscalls;

import fr.ensimag.arm.pseudocode.Operand;
import fr.ensimag.arm.pseudocode.Syscall;

public class Write extends Syscall {
    @Override
    public String getDescription() {
        return "write(int fd, const void *buf, size_t count)";
    }

    @Override
    public int getNumber() {
        return 4;
    }

    public Write(Operand fd, Operand buf, Operand count) {
        super(fd, buf, count);
    }
}
