package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tree.AbstractMain;
import fr.ensimag.deca.tree.EmptyMain;
import fr.ensimag.deca.tree.StringLiteral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MainTest {
    @Mock
    DecacCompiler compiler;
    EnvironmentExp env;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        env = new EnvironmentExp(null);
        SymbolTable symbolTable = new SymbolTable();
        when(compiler.createSymbol(any(String.class))).thenAnswer(
                invocationOnMock -> {
                    String symbol = invocationOnMock.getArgument(0, String.class);
                    return symbolTable.create(symbol);
                });
        when(compiler.getType("int"))
                .thenReturn(new IntType(symbolTable.create("int")));
    }

    @Test
    public void testEmptyMain() throws ContextualError {
        EmptyMain emptyMain = new EmptyMain();
        emptyMain.verifyMain(compiler);
        assertTrue(emptyMain.checkAllDecorations());
        assertEquals("", emptyMain.decompile());
    }

    @Test
    public void testMain() throws ContextualError {
        Main main = new Main(new ListDeclVar(), new ListInst());

        main.verifyMain(compiler);
        assertTrue(main.checkAllDecorations());
        assertEquals("{\n}\n", main.decompile());
    }
}
