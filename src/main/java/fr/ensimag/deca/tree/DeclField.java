package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;
import fr.ensimag.ima.pseudocode.IMAProgram;
import java.io.PrintStream;
import fr.ensimag.arm.pseudocode.ARMProgram;
/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclField extends AbstractDeclVar {

    private AbstractIdentifier name;
    private AbstractExpr expression;

    public DeclField(AbstractIdentifier name, AbstractExpr expression) {
        Validate.notNull(name);
        Validate.notNull(expression);
        this.name = name;
        this.expression = expression;
    }

    public AbstractExpr getExpr() {
        return expression;
    }

    public AbstractIdentifier getName() {
        return name;
    }

    public void setName(AbstractIdentifier name){
        this.name = name;
    }
    public void setExpression(AbstractExpr expr){
        this.expression = expr;
    }

    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
                                 EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {/*
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
        name.decompile(s);
        s.print(" ");
        expression.decompile(s);
        s.print(";");
    }

    @Override
    public void codeGen(ARMProgram program){}
    @Override
    public void codeGen(IMAProgram program){}
    @Override
    protected void iterChildren(TreeFunction f) {
        name.iter(f);
        expression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        name.prettyPrint(s, prefix, false);
        expression.prettyPrint(s, prefix, false);
    }


}
