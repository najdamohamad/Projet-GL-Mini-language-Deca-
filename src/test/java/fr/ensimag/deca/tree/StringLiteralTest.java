package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
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

public class StringLiteralTest {
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
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getType("float"))
                .thenReturn(new FloatType(symbolTable.create("float")));
    }

    @Test
    public void testStringLiteral() throws ContextualError {
        StringLiteral stringLiteral = new StringLiteral("test");
        stringLiteral.verifyExpr(compiler, env, null);
        assertEquals("\"test\"", stringLiteral.decompile());
        assertTrue(stringLiteral.getType().isString());
    }
}
