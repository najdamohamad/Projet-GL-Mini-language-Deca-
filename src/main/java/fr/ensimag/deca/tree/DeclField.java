package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclField extends AbstractDeclField {

    private AbstractIdentifier varName;
    private AbstractExpr expression;

    public DeclField(AbstractIdentifier name, AbstractExpr expression) {
        Validate.notNull(name);
        Validate.notNull(expression);
        this.varName = name;
        this.expression = expression;
    }

    public void setVarName(AbstractIdentifier varName) {
        this.varName = varName;
    }

    public void setExpression(AbstractExpr expr) {
        this.expression = expr;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        varName.decompile(s);
        s.print(" ");
        expression.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        varName.iter(f);
        expression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        varName.prettyPrint(s, prefix, false);
        expression.prettyPrint(s, prefix, false);
    }


    @Override
    protected void verifyDeclField(DecacCompiler compiler, ClassDefinition superClass, ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void verifyDeclFieldInit(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
