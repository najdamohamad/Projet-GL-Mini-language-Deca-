package fr.ensimag.deca.context.helloworld;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tree.AbstractExpr;
import fr.ensimag.deca.tree.ListExpr;
import fr.ensimag.deca.tree.ListInst;
import fr.ensimag.deca.tree.Print;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for the Print node.
 *
 * @date 07/01/2022
 */
public class TestPrint {
    Type STRING;
    Type VOID;
    Type BOOLEAN;

    DecacCompiler compiler;
    @Mock
    AbstractExpr stringExpr;
    @Mock
    AbstractExpr booleanExpr;

    @BeforeEach
    public void setup() throws ContextualError {
        MockitoAnnotations.initMocks(this);
        compiler = new DecacCompiler(null, null);
        // Need to pass symbols because we need them for the error message. Refactor this in the verification
        STRING = new StringType(compiler.createSymbol("string"));
        VOID = new VoidType(compiler.createSymbol("void"));
        BOOLEAN = new BooleanType(compiler.createSymbol("boolean"));
        when(stringExpr.verifyExpr(compiler, null, null)).thenReturn(STRING);
        when(booleanExpr.verifyExpr(compiler, null, null)).thenReturn(BOOLEAN);
    }

    @Test
    public void testPrintString() throws ContextualError {
        ListExpr exprPrint = new ListExpr();
        exprPrint.add(stringExpr);
        Print print = new Print(false, exprPrint);
        ListInst listInst = new ListInst();
        listInst.add(print);

        listInst.verifyListInst(compiler, null, null, VOID);
        assertTrue(print.checkAllDecorations());

        // check that the mocks have been called properly.
        verify(stringExpr).verifyExpr(compiler, null, null);
    }

    /**
     * Test with a boolean. This test must fail, because print cannot accept a boolean
     */
    @Test
    public void testPrintBool() throws ContextualError {
        ListExpr exprPrint = new ListExpr();
        exprPrint.add(booleanExpr);
        Print print = new Print(false, exprPrint);
        ListInst listInst = new ListInst();
        listInst.add(print);

        Exception exception = assertThrows(ContextualError.class, () -> {
            listInst.verifyListInst(compiler, null, null, VOID);
        });

        String expectedMessage = "TypeError: s'attendait a string ou float ou int, a obtenu boolean";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertTrue(print.checkAllDecorations());

        // check that the mocks have been called properly.
        verify(booleanExpr).verifyExpr(compiler, null, null);
    }
}