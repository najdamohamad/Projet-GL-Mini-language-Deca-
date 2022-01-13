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

/**
 * Full if/else if/else statement.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class InstTest {

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
        when(compiler.getType("boolean"))
                .thenReturn(new BooleanType(symbolTable.create("boolean")));
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getType("void"))
                .thenReturn(new VoidType(symbolTable.create("void")));
        when(compiler.getType("int"))
                .thenReturn(new IntType(symbolTable.create("int")));
        when(compiler.getTypeDefinition("boolean"))
                .thenReturn(new TypeDefinition(new BooleanType(symbolTable.create("boolean")), null));
    }

    @Test
    public void testExprInst() {
        ListDeclVar decls = new ListDeclVar();
        decls.add(new DeclVar(
                new Identifier(compiler.createSymbol("boolean")),
                new Identifier(compiler.createSymbol("answer")),
                new NoInitialization()
        ));
        ListExpr printArguments = new ListExpr();
        printArguments.add(new Assign(
                new Identifier(compiler.createSymbol("answer")),
                // new FloatLiteral(42.0f)
                new BooleanLiteral(true)
        ));
        ListInst insts = new ListInst();
        insts.add(new Println(false, printArguments));
        Main main = new Main(decls, insts);

        Exception e = assertThrows(ContextualError.class, () -> {
            main.verifyMain(compiler);
        });

        String expectedMessage = "TypeError: type(s) incorrect(s) dans "
                + "l'instruction `println((answer = true));`, "
                + "attendu `int` ou bien `float`, mais trouvé `boolean`.";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testIfThenElse() throws ContextualError {
        AbstractExpr cond = new BooleanLiteral(true);
        ListInst branchTrue = new ListInst();
        branchTrue.add(new BooleanLiteral(true));
        ListInst branchFalse = new ListInst();
        branchFalse.add(new BooleanLiteral(false));
        IfThenElse stmt = new IfThenElse(cond, branchTrue, branchFalse);

        stmt.verifyInst(compiler, env, null, compiler.getType("void"));
        assertTrue(stmt.checkAllDecorations());
        assertEquals("if(true){\n\ttrue;\n} else {\n\tfalse;\n}", stmt.decompile());
    }

    @Test
    public void testIfBadType() {
        AbstractExpr cond = new StringLiteral("foo");
        ListInst branchTrue = new ListInst();
        branchTrue.add(new BooleanLiteral(true));
        ListInst branchFalse = new ListInst();
        branchFalse.add(new BooleanLiteral(false));
        IfThenElse stmt = new IfThenElse(cond, branchTrue, branchFalse);

        Exception e = assertThrows(ContextualError.class, () -> {
            stmt.verifyInst(compiler, env, null, compiler.getType("void"));
        });

        String expected = "TypeError: type incorrect pour expression `\"foo\"`, attendu `boolean` mais trouvé `string`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testNestedIf() throws ContextualError {
        AbstractExpr cond = new BooleanLiteral(true);
        ListInst branchTrue = new ListInst();
        branchTrue.add(new BooleanLiteral(true));
        ListInst branchFalse = new ListInst();
        branchFalse.add(new BooleanLiteral(false));
        IfThenElse stmt = new IfThenElse(cond, branchTrue, branchFalse);

        AbstractExpr cond2 = new BooleanLiteral(true);
        ListInst branchTrue2 = new ListInst();
        branchTrue2.add(new BooleanLiteral(true));
        ListInst branchFalse2 = new ListInst();
        branchFalse2.add(stmt);
        IfThenElse stmt2 = new IfThenElse(cond2, branchTrue2, branchFalse2);

        stmt2.verifyInst(compiler, env, null, compiler.getType("void"));
        assertTrue(stmt.checkAllDecorations());
        assertEquals(
                "if(true){" +
                        "\n\ttrue;" +
                        "\n} else {" +
                        "\n\tif(true){" +
                        "\n\t\ttrue;" +
                        "\n\t} else {" +
                        "\n\t\tfalse;" +
                        "\n\t}" +
                        "\n}", stmt2.decompile());
    }

    @Test
    public void testNoop() throws ContextualError {
        NoOperation noop = new NoOperation();
        noop.verifyInst(compiler, env, null, compiler.getType("void"));
        assertTrue(noop.checkAllDecorations());
        assertEquals(";", noop.decompile());
    }

    @Test
    public void testReturn() throws ContextualError {
        Return ret = new Return(new IntLiteral(3));
        ret.verifyInst(compiler, env, null, compiler.getType("int"));
        assertTrue(ret.checkAllDecorations());
        assertEquals("return 3;", ret.decompile());
    }

    @Test
    public void testVoidReturn() throws ContextualError {
        Return ret = new Return(new Null());

        Exception e = assertThrows(ContextualError.class, () -> {
            ret.verifyInst(compiler, env, null, compiler.getType("void"));
        });

        String expected = "TypeError: il est impossible de retourner une valeur quand la méthode a un type de retour `void`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testVoidReturnMismatched() throws ContextualError {
        Return ret = new Return(new IntLiteral(3));
        Exception e = assertThrows(ContextualError.class, () -> {
            ret.verifyInst(compiler, env, null, compiler.getType("boolean"));
        });

        String expected = "TypeError: type incorrect pour expression `3`, attendu `boolean` mais trouvé `int`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testWhile() throws ContextualError {
        ListInst body = new ListInst();
        body.add(new BooleanLiteral(false));
        While wh = new While(new BooleanLiteral(true), body);
        wh.verifyInst(compiler, env, null, compiler.getType("boolean"));
        assertTrue(wh.checkAllDecorations());
        assertEquals(
                "while(true){" +
                        "\n\tfalse;" +
                        "\n}",
                wh.decompile());
    }

    @Test
    public void testWhileBadType() throws ContextualError {
        ListInst body = new ListInst();
        body.add(new StringLiteral("foo"));
        While wh = new While(new StringLiteral("foo"), body);

        Exception e = assertThrows(ContextualError.class, () -> {
            wh.verifyInst(compiler, env, null, compiler.getType("boolean"));
        });

        String expected = "TypeError: type incorrect pour expression `\"foo\"`, attendu `boolean` mais trouvé `string`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}
