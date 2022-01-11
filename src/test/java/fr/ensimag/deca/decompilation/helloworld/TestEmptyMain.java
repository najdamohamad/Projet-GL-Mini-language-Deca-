package fr.ensimag.deca.decompilation.helloworld;

import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tree.AbstractMain;
import fr.ensimag.deca.tree.EmptyMain;
import fr.ensimag.deca.tree.StringLiteral;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmptyMain {
    @Test
    public void testEmptyMain() {
        EmptyMain emptyMain = new EmptyMain();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);

        emptyMain.decompile(indentPrintStream);

        System.out.println(baos);

        assertEquals(""  ,baos.toString() );
    }
}
