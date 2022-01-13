package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.StringType;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EnvironmentExpTest {
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
                .thenReturn(new StringType(compiler.createSymbol("string")));
    }

    void testDoubleDef() throws EnvironmentExp.DoubleDefException {
        SymbolTable.Symbol x = compiler.createSymbol("x");
        env.declare(x, new VariableDefinition(compiler.getType("string"), null));

        assertThrows(EnvironmentExp.DoubleDefException.class, () -> {
            env.declare(x, new VariableDefinition(compiler.getType("string"), null));
        });
    }
}
