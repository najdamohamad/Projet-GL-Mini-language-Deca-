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
        when(compiler.getType("void"))
                .thenReturn(new BooleanType(symbolTable.create("void")));
        when(compiler.getType("string"))
                .thenReturn(new BooleanType(symbolTable.create("string")));
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
        assertEquals("if(true){\n\ttrue;\n} else {\n\tfalse;\n}\n", stmt.decompile());
    }

    @Test
    public void testIfBadType() throws ContextualError {
        AbstractExpr cond = new StringLiteral("foo");
        ListInst branchTrue = new ListInst();
        branchTrue.add(new BooleanLiteral(true));
        ListInst branchFalse = new ListInst();
        branchFalse.add(new BooleanLiteral(false));
        IfThenElse stmt = new IfThenElse(cond, branchTrue, branchFalse);

        stmt.verifyInst(compiler, env, null, compiler.getType("void"));
        assertTrue(stmt.checkAllDecorations());
        assertEquals("if(true){\n\ttrue;\n} else {\n\tfalse;\n}", stmt.decompile());
    }
}
