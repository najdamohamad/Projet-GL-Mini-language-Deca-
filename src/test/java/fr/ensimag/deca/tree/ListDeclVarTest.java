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

public class ListDeclVarTest {
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
    public void testListOneElement() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new StringLiteral("init"));
        DeclVar decl = new DeclVar(type, name, initialization);

        ListDeclVar listDeclVar = new ListDeclVar();
        listDeclVar.add(decl);

        listDeclVar.verifyListDeclVariable(compiler, env, null);
        assertTrue(listDeclVar.checkAllDecorations());
        assertEquals(listDeclVar.decompile(), "string x = \"init\";");
    }

    @Test
    public void testListTwoElements() throws ContextualError {
        Identifier type = new Identifier(compiler.createSymbol("string"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new StringLiteral("init"));
        DeclVar decl = new DeclVar(type, name, initialization);

        Identifier type2 = new Identifier(compiler.createSymbol("string"));
        Identifier name2 = new Identifier(compiler.createSymbol("y"));
        Initialization initialization2 = new Initialization(
                new StringLiteral("init2"));
        DeclVar decl2 = new DeclVar(type2, name2, initialization2);

        ListDeclVar listDeclVar = new ListDeclVar();
        listDeclVar.add(decl);
        listDeclVar.add(decl2);

        listDeclVar.verifyListDeclVariable(compiler, env, null);
        assertTrue(listDeclVar.checkAllDecorations());
        assertEquals(listDeclVar.decompile(), "string x = \"init\";\nstring y = \"init2\";");
    }

    @Test
    public void testEmptyListDecl() throws ContextualError {
        ListDeclVar listDeclVar = new ListDeclVar();
        listDeclVar.verifyListDeclVariable(compiler, env, null);
        assertTrue(listDeclVar.checkAllDecorations());
        assertEquals(listDeclVar.decompile(), "");
    }
}
