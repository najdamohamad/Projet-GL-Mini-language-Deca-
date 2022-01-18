package fr.ensimag.deca.codegen.arm;

import fr.ensimag.arm.pseudocode.Register;
import fr.ensimag.arm.pseudocode.instructions.PUSH;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PushTest {
    PrintStream ps;
    ByteArrayOutputStream s;

    @BeforeEach
    public void setup() {
        s = new ByteArrayOutputStream();
        ps = new PrintStream(s);
    }

    @Test
    public void shouldDisplay() {
        PUSH push = new PUSH(Register.R7);
        push.display(ps);
        assertEquals("push {r7}\n", s.toString());
    }

    @Test
    public void shouldDisplayMany() {
        PUSH push = new PUSH(Register.R7, Register.R0, Register.R1);
        push.display(ps);
        assertEquals("push {r7, r0, r1}\n", s.toString());
    }
}
