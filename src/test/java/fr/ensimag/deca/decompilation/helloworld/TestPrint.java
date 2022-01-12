package fr.ensimag.deca.decompilation.helloworld;

import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tree.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPrint {
    @Test
    public void testPrint()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);


        ListExpr listExpr = new ListExpr();

        listExpr.add(new StringLiteral("hello"));
        listExpr.add(new StringLiteral("world"));


        Print print = new Print(false , listExpr) ;

        print.decompile(indentPrintStream);

        assertEquals("print(\"hello\",\"world\");" ,stream.toString());
    }

    @Test
    public void testPrintX()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);


        ListExpr listExpr = new ListExpr();

        listExpr.add(new StringLiteral("hello"));



        Print print = new Print(true , listExpr) ;


        print.decompile(indentPrintStream);

        assertEquals("printx(\"hello\");" ,stream.toString());
    }
}
