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

public class LiteralTest {
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
        when(compiler.getType("void"))
                .thenReturn(new VoidType(symbolTable.create("void")));
        when(compiler.getType("null"))
                .thenReturn(new VoidType(symbolTable.create("null")));
        when(compiler.getTypeDefinition("null"))
                .thenReturn(new TypeDefinition(new VoidType(symbolTable.create("null")), null));
        when(compiler.getType("int"))
                .thenReturn(new IntType(symbolTable.create("int")));
        when(compiler.getType("float"))
                .thenReturn(new FloatType(symbolTable.create("float")));
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getType("boolean"))
                .thenReturn(new BooleanType(symbolTable.create("boolean")));
    }

    @Test
    public void testIntLiteral() throws ContextualError {
        IntLiteral exp = new IntLiteral(1);
        exp.verifyExpr(compiler, env, null);
        assertEquals(exp.decompile(), "1");
    }

    @Test
    public void testFloatLiteral() throws ContextualError {
        FloatLiteral exp = new FloatLiteral(1);
        exp.verifyExpr(compiler, env, null);
        assertEquals(exp.decompile(), "0x1.0p0");
    }

    @Test
    public void testStringLiteral() throws ContextualError {
        StringLiteral exp = new StringLiteral("foo");
        exp.verifyExpr(compiler, env, null);
        assertEquals(exp.decompile(), "\"foo\"");
    }

    @Test
    public void testTrueBoolean() throws ContextualError {
        BooleanLiteral trueBool = new BooleanLiteral(true);
        trueBool.verifyExpr(compiler, env, null);
        assertEquals(trueBool.decompile(), "true");
    }

    @Test
    public void testFalseBoolean() throws ContextualError {
        BooleanLiteral trueBool = new BooleanLiteral(false);
        trueBool.verifyExpr(compiler, env, null);
        assertEquals(trueBool.decompile(), "false");
    }

    @Test
    public void testNull() throws ContextualError {
        Null n = new Null();
        n.verifyExpr(compiler, env, null);
        assertEquals(n.decompile(), "null");
    }
}
