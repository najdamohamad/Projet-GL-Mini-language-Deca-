package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclMethodAsm extends DeclMethod {


    final private AbstractIdentifier type;
    final private AbstractIdentifier name;
    final private ListDeclParam params;
    final private String text;
    final private Location location;

    public DeclMethodAsm(AbstractIdentifier type, AbstractIdentifier name, ListDeclParam params, String text, Location location) {
        Validate.notNull(type);
        Validate.notNull(name);
        Validate.notNull(params);
        validate.notNull(text);
        validate.notNull(location);
        this.type = type;
        this.name = name;
        this.params = params;
        this.text = text;
        this.location = location;
    }
/*
    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
                                 EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type varType = type.verifyType(compiler);
        if (varType.sameType(compiler.getType("void"))) {
            String message = "TypeError: il est impossible de déclarer des identificateurs de type `void`.";
            throw new ContextualError(message, getLocation());
        }
        try {
            ExpDefinition varDefinition = new VariableDefinition(varType, getLocation());
            varName.setDefinition(varDefinition);
            localEnv.declare(varName.getName(), varDefinition);
            initialization.verifyInitialization(compiler, varType, localEnv, currentClass);
        } catch (EnvironmentExp.DoubleDefException e) {
            String message = "ScopeError: l'identificateur `"
                    + varName.decompile() + "` ne peut être défini plus qu'une fois.";
            throw new ContextualError(message, getLocation());
        }
    }


    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        varName.decompile(s);
        initialization.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        varName.iter(f);
        initialization.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }

 */
}