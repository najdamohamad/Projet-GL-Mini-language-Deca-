package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.StringType;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests identifiers and their relations with environments.
 */
public class IdentifierTest {
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
    }

    @Test
    public void testVariableInEnv() throws EnvironmentExp.DoubleDefException, ContextualError {
        SymbolTable.Symbol x = compiler.createSymbol("x");
        Identifier identifier = new Identifier(x);
        env.declare(x, new VariableDefinition(new StringType(null), null));
        assertTrue(env.get(x).getType().sameType(new StringType(null)));
        identifier.verifyExpr(compiler, env, null);
        assertTrue(identifier.getVariableDefinition().getType().sameType(new StringType(null)));
    }
}
