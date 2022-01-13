package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReadExprTest {
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
        when(compiler.getType("float"))
                .thenReturn(new FloatType(symbolTable.create("float")));
    }

    @Test
    public void testReadFloat() throws ContextualError {
        ReadFloat readFloat = new ReadFloat();
        Type t = readFloat.verifyExpr(compiler, env, null);
        assertTrue(t.isFloat());
        assertTrue(readFloat.checkAllDecorations());
        assertEquals("readFloat()", readFloat.decompile());
    }

    @Test
    public void testReadInt() throws ContextualError {
        ReadInt readInt = new ReadInt();
        Type t = readInt.verifyExpr(compiler, env, null);
        assertTrue(t.isInt());
        assertTrue(readInt.checkAllDecorations());
        assertEquals("readInt()", readInt.decompile());
    }
}
