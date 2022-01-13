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

public class LValueTest {
    @Mock
    DecacCompiler compiler;
    EnvironmentExp env;

    @BeforeEach
    public void setup() throws ContextualError {
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
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getTypeDefinition("int"))
                .thenReturn(new TypeDefinition(new IntType(symbolTable.create("int")), null));

        // Define a single identifier of type int.
        Identifier type = new Identifier(compiler.createSymbol("int"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new IntLiteral(3));
        DeclVar decl = new DeclVar(type, name, initialization);
        decl.verifyDeclVar(compiler, env, null);
    }

    @Test
    public void lValueRValueTest() throws ContextualError {
        AbstractLValue identifier = new Identifier(compiler.createSymbol("x"));
        IntLiteral i = new IntLiteral(3);
        identifier.verifyExpr(compiler, env, null);
        i.verifyRValue(compiler, env, null,
                new IntType(null));
    }
}
