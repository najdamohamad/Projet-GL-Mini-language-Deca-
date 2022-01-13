package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    }

    @Test
    public void testAnd() throws ContextualError {
        BooleanLiteral a = new BooleanLiteral(true);
        BooleanLiteral b = new BooleanLiteral(true);
        And and = new And(a, b);
        and.verifyExpr(compiler, env, null);
    }
}
