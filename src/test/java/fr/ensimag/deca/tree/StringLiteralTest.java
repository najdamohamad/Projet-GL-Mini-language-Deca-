package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tree.StringLiteral;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringLiteralTest {
    @Test
    public void testStringLiteral() {
        StringLiteral stringLiteral = new StringLiteral("test");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);

        stringLiteral.decompile(indentPrintStream);

        assertEquals("\"test\"" ,stream.toString());
    }
}
