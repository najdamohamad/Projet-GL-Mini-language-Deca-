package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeclVarTest {
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
        when(compiler.getType("int"))
                .thenReturn(new IntType(symbolTable.create("int")));
        when(compiler.getTypeDefinition("string"))
                .thenReturn(new TypeDefinition(new StringType(symbolTable.create("string")), null));
    }

    @Test
    public void testDeclVar() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new StringLiteral("init"));

        DeclVar decl = new DeclVar(type, name, initialization);
        decl.verifyDeclVar(compiler, env, null);
        assertTrue(decl.checkAllDecorations());
    }

    @Test
    public void testBadlyTypedDecl() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new IntLiteral(1));

        DeclVar decl = new DeclVar(type, name, initialization);
        assertThrows(ContextualError.class, () -> {
            decl.verifyDeclVar(compiler, env, null);
        });
        assertTrue(decl.checkAllDecorations());
    }

    @Test
    public void testDoubleDeclVar() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new StringLiteral("init"));

        DeclVar decl = new DeclVar(type, name, initialization);
        decl.verifyDeclVar(compiler, env, null);
        DeclVar decl2 = new DeclVar(type, name, initialization);
        Exception e = assertThrows(ContextualError.class, () -> {
            decl2.verifyDeclVar(compiler, env, null);
        });

        String expected = "ScopeError: l'identificateur `x` ne peut être défini plus qu'une fois.";
        String actual = e.getMessage();
        assertTrue(expected.contains(actual));
        assertTrue(decl.checkAllDecorations());
    }

    @Test
    public void testVariableNamedString() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("string"));
        Initialization initialization = new Initialization(
                new StringLiteral("init"));

        DeclVar decl = new DeclVar(type, name, initialization);
        decl.verifyDeclVar(compiler, env, null);
        assertTrue(decl.checkAllDecorations());
    }
}
