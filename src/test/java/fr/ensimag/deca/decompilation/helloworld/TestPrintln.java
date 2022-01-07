package fr.ensimag.deca.decompilation.helloworld;

import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tree.ListExpr;
import fr.ensimag.deca.tree.Print;
import fr.ensimag.deca.tree.Println;
import fr.ensimag.deca.tree.StringLiteral;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPrintln {
    @Test
    public void testPrintln()
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);


        ListExpr listExpr = new ListExpr();

        listExpr.add(new StringLiteral("hello"));
        listExpr.add(new StringLiteral("world"));


        Println println = new Println(false , listExpr) ;

        println.decompile(indentPrintStream);

        assertEquals("println(\"hello\",\"world\")" ,stream.toString());
    }



    @Test
    public void testPrintlnx()

    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(stream);
        IndentPrintStream indentPrintStream = new IndentPrintStream(printStream);


        ListExpr listExpr = new ListExpr();

        listExpr.add(new StringLiteral("hello"));
        listExpr.add(new StringLiteral("world"));


        Println printlnx = new Println(true , listExpr) ;

        printlnx.decompile(indentPrintStream);

        assertEquals("printlnx(\"hello\",\"world\")" ,stream.toString());
    }

}