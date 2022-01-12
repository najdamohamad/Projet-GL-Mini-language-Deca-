package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests the EnvironmentType class.
 */
public class EnvironmentTypeTest {
    @Mock
    DecacCompiler compiler;

    String[] baseTypeNames = {"void", "boolean", "float", "int", "string"};
    Type[] baseTypes = {new VoidType(null),
            new BooleanType(null), new FloatType(null),
            new IntType(null), new StringType(null)};

    @BeforeEach
    public void setup() throws ContextualError {
        MockitoAnnotations.initMocks(this);

        SymbolTable symbolTable = new SymbolTable();
        when(compiler.createSymbol(any(String.class))).thenAnswer(
                invocationOnMock -> {
                    String symbol = invocationOnMock.getArgument(0, String.class);
                    return symbolTable.create(symbol);
                });
    }

    @Test
    public void testEnvironmentType() {
        EnvironmentType e = new EnvironmentType(compiler);
        for (int i = 0; i < baseTypes.length; i++) {
            String baseType = baseTypeNames[i];
            Type type = baseTypes[i];
            TypeDefinition typedef = e.get(compiler.createSymbol(baseType));
            assertEquals("type", typedef.getNature());
            assertTrue(type.sameType(typedef.getType()));
        }
    }
}
