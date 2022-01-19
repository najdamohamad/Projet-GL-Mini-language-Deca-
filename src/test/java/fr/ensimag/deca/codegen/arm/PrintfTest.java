package fr.ensimag.deca.codegen.arm;

import fr.ensimag.arm.pseudocode.ImmediateInteger;
import fr.ensimag.arm.pseudocode.Register;
import fr.ensimag.arm.pseudocode.libc.Printf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintfTest {
    PrintStream ps;
    ByteArrayOutputStream s;

    @BeforeEach
    public void setup() {
        s = new ByteArrayOutputStream();
        ps = new PrintStream(s);
    }

    @Test
    public void testPrintf() {
        ImmediateInteger a = new ImmediateInteger(1);
        ImmediateInteger b = new ImmediateInteger(2);
        Printf printf = new Printf(a, b);
        printf.display(ps);
        assertEquals("", s.toString());
    }
}
