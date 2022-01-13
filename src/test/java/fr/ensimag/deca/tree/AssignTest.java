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

public class AssignTest {
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
        when(compiler.getType("float"))
                .thenReturn(new FloatType(symbolTable.create("float")));
        when(compiler.getType("string"))
                .thenReturn(new StringType(symbolTable.create("string")));
        when(compiler.getType("boolean"))
                .thenReturn(new StringType(symbolTable.create("boolean")));
        when(compiler.getTypeDefinition("int"))
                .thenReturn(new TypeDefinition(new IntType(symbolTable.create("int")), null));
        when(compiler.getTypeDefinition("float"))
                .thenReturn(new TypeDefinition(new FloatType(symbolTable.create("float")), null));
        when(compiler.getTypeDefinition("null"))
                .thenReturn(new TypeDefinition(new NullType(symbolTable.create("null")), null));

        // Define a single identifier of type int.
        Identifier type = new Identifier(compiler.createSymbol("int"));
        Identifier name = new Identifier(compiler.createSymbol("x"));
        Initialization initialization = new Initialization(
                new IntLiteral(3));
        DeclVar decl = new DeclVar(type, name, initialization);
        decl.verifyDeclVar(compiler, env, null);
        // Another identifier of type y.
        Identifier type2= new Identifier(compiler.createSymbol("float"));
        Identifier name2 = new Identifier(compiler.createSymbol("y"));
        Initialization initialization2 = new Initialization(
                new FloatLiteral(3));
        DeclVar decl2 = new DeclVar(type2, name2, initialization2);
        decl2.verifyDeclVar(compiler, env, null);
    }

    @Test
    public void testAssign() throws ContextualError {
        Identifier id = new Identifier(compiler.createSymbol("x"));
        IntLiteral i = new IntLiteral(3);
        Assign assign = new Assign(id, i);
        assign.verifyExpr(compiler, env, null);
        assertTrue(assign.checkAllDecorations());
        assertEquals("(x=3)", assign.decompile());
    }

    @Test
    public void testAssignInvalid() throws ContextualError {
        Identifier id = new Identifier(compiler.createSymbol("x"));
        StringLiteral i = new StringLiteral("foo");
        Assign assign = new Assign(id, i);
        Exception e = assertThrows(ContextualError.class, () -> {
            assign.verifyExpr(compiler, env, null);
        });
        String expected = "TypeError: type incorrect pour expression `\"foo\"`, attendu `int` mais trouvé `string`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testAssignVoid() throws ContextualError {
        Identifier id = new Identifier(compiler.createSymbol("x"));
        Null n = new Null();
        Assign assign = new Assign(id, n);
        Exception e = assertThrows(ContextualError.class, () -> {
            assign.verifyExpr(compiler, env, null);
        });
        String expected = "TypeError: type incorrect pour expression `null`, attendu `int` mais trouvé `null`.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testAssignFloatToInt() throws ContextualError {
        Identifier id = new Identifier(compiler.createSymbol("y"));
        IntLiteral i = new IntLiteral(5);
        Assign assign = new Assign(id, i);
        assertTrue(assign.checkAllDecorations());
        assertEquals("(y=5)", assign.decompile());
    }
}
