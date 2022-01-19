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

    private final AbstractIdentifier type;
    private final AbstractIdentifier varName;
    private final AbstractInitialization initialization;
    private final Visibility visibility;

    public DeclField(Visibility visibility, AbstractIdentifier type,
                     AbstractIdentifier varName, AbstractInitialization initialization) {
        Validate.notNull(visibility);
        Validate.notNull(type);
        Validate.notNull(varName);
        Validate.notNull(initialization);
        this.visibility = visibility;
        this.type = type;
        this.varName = varName;
        this.initialization = initialization;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        varName.decompile(s);
        s.print(" ");
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


    @Override
    protected void verifyDeclField(DecacCompiler compiler, EnvironmentExp localEnv,
                                   ClassDefinition superClass, ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void verifyDeclFieldInit(DecacCompiler compiler, EnvironmentExp localEnv,
                                       ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public Visibility getVisibility() {
        return visibility;
    }
}
