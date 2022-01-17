package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Param extends AbstractExpr {

    private AbstractIdentifier Type;
    private AbstractIdentifier Ident;

    public AbstractIdentifier getIdent() {
        return this.Ident;
    }
    public AbstractIdentifier getType() {
        return this.Type;
    }

    protected void setIdent(AbstractIdentifier Ident) {
        Validate.notNull(Ident);
        this.Ident = Ident;
    }

    protected void setType(AbstractIdentifier Type) {
        Validate.notNull(Type);
        this.Type = Type;
    }
    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }


    public Param(AbstractIdentifier Ident, AbstractIdentifier Type) {
        Validate.notNull(Ident, "ident cannot be null");
        Validate.notNull(Type, "Type cannot be null");
        this.Ident = Ident;
        this.Type = Type;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        getType().decompile(s);
        s.print(" ");
        getIdent().decompile(s);
    }

    protected String getOperatorName() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    ;

    @Override
    protected void iterChildren(TreeFunction f) {
        getType().iter(f);
        getIdent().iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        getType().prettyPrint(s, prefix, false);
        getIdent().prettyPrint(s, prefix, false);
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}