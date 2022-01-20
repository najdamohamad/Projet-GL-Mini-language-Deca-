package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tree.*;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReadFloatTest {

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
        when(compiler.getType("float"))
                .thenReturn(new FloatType(symbolTable.create("float")));
        when(compiler.getTypeDefinition("float"))
                .thenReturn(new TypeDefinition(new FloatType(symbolTable.create("float")), null));
    }

    @Test
    public void testCodeGenReadFloat() throws ContextualError, IOException {
        ReadFloat r = new ReadFloat();

        IMAProgram imaProgram = new IMAProgram(15, true);
        r.verifyExpr(compiler, env, null);
        r.codeGen(imaProgram);
        String actualProgram = imaProgram.display();
        String expectedProgram = Files
                .readString(Paths.get("src/test/java/fr/ensimag/deca/codegen/readFloatTest"));
        System.out.println(actualProgram);
        System.out.println(expectedProgram);
        assertEquals(actualProgram, expectedProgram);

    }
}
