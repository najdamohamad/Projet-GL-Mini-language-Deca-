package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.LessThan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OpCmpTest {
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
                .thenReturn(new StringType(symbolTable.create("boolean")));
    }

    @Test
    public void testGreater() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new Greater(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 > 4)", e.decompile());
    }

    @Test
    public void testGreaterOrEqual() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new GreaterOrEqual(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 >= 4)", e.decompile());
    }

    @Test
    public void testLower() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new Lower(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 < 4)", e.decompile());
    }

    @Test
    public void testLowerOrEqual() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new LowerOrEqual(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 <= 4)", e.decompile());
    }

    @Test
    public void testEquals() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new Equals(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 == 4)", e.decompile());
    }

    @Test
    public void testEqualsBoolean() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        BooleanLiteral b = new BooleanLiteral(false);
        AbstractOpCmp e = new Equals(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(true == false)", e.decompile());
    }

    @Test
    public void testNotEquals() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e = new NotEquals(a, b);
        e.verifyExpr(compiler, env, null);
        assertTrue(e.checkAllDecorations());
        assertEquals("(2 != 4)", e.decompile());
    }

    @Test
    public void testIntStringComp() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        StringLiteral b = new StringLiteral("foo");
        AbstractOpCmp exp = new Greater(a, b);

        Exception e = assertThrows(ContextualError.class, () -> {
            exp.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression d'inégalité `(2 > \"foo\")`, attendu `int` ou bien `float`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testStringIntComp() throws ContextualError {
        StringLiteral a = new StringLiteral("foo");
        IntLiteral b = new IntLiteral(2);
        AbstractOpCmp exp = new Greater(a, b);

        Exception e = assertThrows(ContextualError.class, () -> {
            exp.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression d'inégalité `(\"foo\" > 2)`, attendu `int` ou bien `float`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testFloatComp() throws ContextualError {
        FloatLiteral a = new FloatLiteral(2);
        FloatLiteral b = new FloatLiteral(4);
        AbstractOpCmp exp = new NotEquals(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertEquals("(0x1.0p1 != 0x1.0p2)", exp.decompile());
    }

    @Test
    public void testFloatIntComp() throws ContextualError {
        FloatLiteral a = new FloatLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp exp = new NotEquals(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertFalse(exp.getLeftOperand() instanceof ConvFloat);
        assertTrue(exp.getRightOperand() instanceof ConvFloat);
        assertEquals("(0x1.0p1 != /* conv float */4)", exp.decompile());
    }

    @Test
    public void testIntFloatComp() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        FloatLiteral b = new FloatLiteral(4);
        AbstractOpCmp exp = new NotEquals(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertTrue(exp.getLeftOperand() instanceof ConvFloat);
        assertFalse(exp.getRightOperand() instanceof ConvFloat);
        assertEquals("(/* conv float */2 != 0x1.0p2)", exp.decompile());
    }

    @Test
    public void testNestedExpr() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        AbstractOpCmp e1 = new NotEquals(a, b);
        IntLiteral c = new IntLiteral(6);
        IntLiteral d = new IntLiteral(8);
        AbstractOpCmp e2 = new NotEquals(c, d);
        AbstractOpCmp exp = new NotEquals(e1, e2);

        Exception e = assertThrows(ContextualError.class, () -> {
            exp.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression de comparaison `((2 != 4) != (6 != 8))`, seuls les types `int`/`float` sont comparables.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}
