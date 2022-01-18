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
public class DeclFieldVis extends AbstractDeclVar {

    final private Visibility v;
    final private AbstractIdentifier type;
    final private AbstractIdentifier name;
    final private AbstractExpr expression;

    public DeclFieldVis(Visibility v, AbstractIdentifier type,AbstractIdentifier name, AbstractExpr expression){
        Validate.notNull(v);
        Validate.notNull(type);
        Validate.notNull(name);
        Validate.notNull(expression);
        this.v = v;
        this.type = type;
        this.name = name;
        this.expression = expression;
    }
    public DeclFieldVis(Visibility v, AbstractIdentifier type, DeclField df) {
        Validate.notNull(v);
        Validate.notNull(type);
        Validate.notNull(df);
        this.v = v;
        this.type = type;
        this.name = df.getName();
        this.expression = df.getExpr();
    }


    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
                                   EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        /*
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
        }*/
    }


    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        name.decompile(s);
        s.print(" ");
        expression.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        expression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        name.prettyPrint(s, prefix, false);
        expression.prettyPrint(s, prefix, true);
    }

}
