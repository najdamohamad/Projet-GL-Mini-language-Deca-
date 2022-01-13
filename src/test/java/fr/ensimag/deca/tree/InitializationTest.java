package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.StringType;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InitializationTest {
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
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
    }

    @Test
    public void testInitialization() throws ContextualError {
        Type type = compiler.getType("string");
        Initialization initialization = new Initialization(
                new StringLiteral("init"));
        initialization.verifyInitialization(compiler, type, env, null);
        assertTrue(initialization.checkAllDecorations());
        assertEquals(" = \"init\"", initialization.decompile());
    }

    @Test
    public void testNoInitialization() throws ContextualError {
        Type type = compiler.getType("string");
        NoInitialization initialization = new NoInitialization();
        initialization.verifyInitialization(compiler, type, env, null);
        assertTrue(initialization.checkAllDecorations());
        assertEquals("", initialization.decompile());
    }
}
