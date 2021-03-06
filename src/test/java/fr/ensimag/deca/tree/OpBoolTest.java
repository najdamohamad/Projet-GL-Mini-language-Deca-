package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OpBoolTest {
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
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getType("boolean"))
                .thenReturn(new BooleanType(symbolTable.create("boolean")));
    }

    @Test
    public void testOr() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        BooleanLiteral b = new BooleanLiteral(true);
        Or or = new Or(a, b);
        or.verifyExpr(compiler, env, null);
        assertTrue(or.checkAllDecorations());
        assertEquals(or.decompile(), "(true || true)");
        assertTrue(or.getType().isBoolean());
    }

    @Test
    public void testAnd() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        BooleanLiteral b = new BooleanLiteral(true);
        And and = new And(a, b);
        and.verifyExpr(compiler, env, null);
        assertTrue(and.checkAllDecorations());
        assertEquals(and.decompile(), "(true && true)");
        assertTrue(and.getType().isBoolean());
    }

    @Test
    public void testBadOr() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        StringLiteral b = new StringLiteral("foo");
        Or or = new Or(a, b);

        Exception e = assertThrows(ContextualError.class, () -> {
            or.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression bool??enne `(true || \"foo\")`, attendu `boolean`";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testBadAnd() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        StringLiteral b = new StringLiteral("foo");
        And and = new And(a, b);

        Exception e = assertThrows(ContextualError.class, () -> {
            and.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression bool??enne `(true && \"foo\")`, attendu `boolean`";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}
