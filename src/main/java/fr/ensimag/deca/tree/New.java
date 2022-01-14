package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class New extends AbstractExpr {

    public AbstractIdentifier getIdent() {
        return Ident;
    }

    protected void setIdent(AbstractIdentifier Ident) {
        Validate.notNull(Ident);
        this.Ident = Ident;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    private AbstractIdentifier Ident;

    public New(AbstractIdentifier Ident) {
        Validate.notNull(Ident, "ident cannot be null");
        this.Ident = Ident;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("new ");
        getIdent().decompile(s);
        s.print("()");
    }

    protected String getOperatorName(){
        return "new ()";
    };

    @Override
    protected void iterChildren(TreeFunction f) {
        Ident.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        Ident.prettyPrint(s, prefix, false);
    }

}