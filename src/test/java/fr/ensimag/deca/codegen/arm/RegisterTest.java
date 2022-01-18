package fr.ensimag.deca.codegen.arm;

import fr.ensimag.arm.pseudocode.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterTest {
    PrintStream ps;
    ByteArrayOutputStream s;

    @BeforeEach
    public void setup() {
        s = new ByteArrayOutputStream();
        ps = new PrintStream(s);
    }

    @Test
    public void testNumeric() {
        Register r6 = Register.getR(6);
        r6.display(ps);
        assertEquals("r6", s.toString());
    }

    @Test
    public void testNumericNotGP() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Register r15 = Register.getR(15);
        });
    }

    @Test
    public void testFP() {
        Register FP = Register.FP;
        FP.display(ps);
        assertEquals("fp", s.toString());
    }
    @Test
    public void testIP() {
        Register IP = Register.IP;
        IP.display(ps);
        assertEquals("ip", s.toString());
    }
    @Test
    public void testSP() {
        Register SP = Register.SP;
        SP.display(ps);
        assertEquals("sp", s.toString());
    }
    @Test
    public void testLR() {
        Register LR = Register.LR;
        LR.display(ps);
        assertEquals("lr", s.toString());
    }
    @Test
    public void testPC() {
        Register PC = Register.PC;
        PC.display(ps);
        assertEquals("pc", s.toString());
    }
}
