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

public class OpArithTest {
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
    }

    @Test
    public void testAddition() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(4);
        Plus plus = new Plus(a, b);
        plus.verifyExpr(compiler, env, null);
        assertTrue(plus.checkAllDecorations());
        assertEquals(plus.getType(), compiler.getType("int"));
        assertEquals(plus.decompile(), "(2+4)");
    }

    @Test
    public void testAdditionIntFloat() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        FloatLiteral b = new FloatLiteral(4);
        Plus plus = new Plus(a, b);
        plus.verifyExpr(compiler, env, null);
        assertTrue(plus.checkAllDecorations());
        assertEquals(plus.getType(), compiler.getType("float"));
        assertTrue(plus.getLeftOperand() instanceof ConvFloat);
        assertFalse(plus.getRightOperand() instanceof ConvFloat);
    }

    @Test
    public void testAdditionFloatInt() throws ContextualError {
        FloatLiteral a = new FloatLiteral(2);
        IntLiteral b = new IntLiteral(4);
        Plus plus = new Plus(a, b);
        plus.verifyExpr(compiler, env, null);
        assertTrue(plus.checkAllDecorations());
        assertEquals(plus.getType(), compiler.getType("float"));
        assertFalse(plus.getLeftOperand() instanceof ConvFloat);
        assertTrue(plus.getRightOperand() instanceof ConvFloat);
    }

    @Test
    public void testAdditionFloat() throws ContextualError {
        FloatLiteral a = new FloatLiteral(2);
        FloatLiteral b = new FloatLiteral(4);
        Plus plus = new Plus(a, b);
        plus.verifyExpr(compiler, env, null);
        assertTrue(plus.checkAllDecorations());
    }

    @Test
    public void testAdditionBadType() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        StringLiteral b = new StringLiteral("abc");
        Plus plus = new Plus(a, b);
        Exception e = assertThrows(ContextualError.class, () -> {
            plus.verifyExpr(compiler, env, null);
        });

        String expected = "TypeError: type(s) incorrect(s) dans `l'expression arithmétique `(2+\"abc\")`, attendu `float` ou bien `int`";
        String actual = e.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void testMinus() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(2);
        AbstractExpr exp = new Minus(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertEquals(exp.decompile(), "(2-2)");
    }

    @Test
    public void testMult() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(2);
        AbstractExpr exp = new Multiply(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertEquals(exp.decompile(), "(2*2)");
    }

    @Test
    public void testDivide() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(2);
        AbstractExpr exp = new Divide(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertEquals(exp.decompile(), "(2/2)");
    }

    @Test
    public void testModulo() throws ContextualError {
        IntLiteral a = new IntLiteral(2);
        IntLiteral b = new IntLiteral(2);
        AbstractExpr exp = new Modulo(a, b);
        exp.verifyExpr(compiler, env, null);
        assertTrue(exp.checkAllDecorations());
        assertEquals(exp.decompile(), "(2%2)");
    }
}
