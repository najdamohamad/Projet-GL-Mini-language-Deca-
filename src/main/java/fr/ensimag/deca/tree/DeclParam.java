package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ParamDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DeclParam extends AbstractDeclParam {

    private AbstractIdentifier type;
    private AbstractIdentifier varName;

    protected void setVarName(AbstractIdentifier varName) {
        Validate.notNull(varName);
        this.varName = varName;
    }

    protected void setType(AbstractIdentifier Type) {
        Validate.notNull(Type);
        this.type = Type;
    }

    public DeclParam(AbstractIdentifier type, AbstractIdentifier varName) {
        Validate.notNull(type, "type cannot be null");
        Validate.notNull(varName, "varName cannot be null");
        this.varName = varName;
        this.type = type;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        varName.decompile(s);
    }

    protected String getOperatorName() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        varName.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
    }

    @Override
    protected Type verifyDeclParamType(DecacCompiler compiler) throws ContextualError {
        Type paramType = type.verifyType(compiler);
        if (paramType.isVoid()) {
            String message = "TypeError: un paramètre ne peut être déclaré avec le type `void`";
            throw new ContextualError(message, getLocation());
        }
        return paramType;
    }

    @Override
    protected void verifyDeclParam(DecacCompiler compiler, EnvironmentExp localEnv) throws ContextualError {
        Type paramType = type.verifyType(compiler);
        try {
            localEnv.declare(varName.getName(), new ParamDefinition(paramType, getLocation()));
        } catch (EnvironmentExp.DoubleDefException e) {
            String message = "ScopeError: le paramètre `" + varName.getName() + "` est défini plus qu'une fois.";
            throw new ContextualError(message, getLocation());
        }
    }
}