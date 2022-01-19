package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DeclClass extends AbstractDeclClass {

    final private AbstractIdentifier identifier;
    final private AbstractIdentifier extension;
    final private ListDeclMethod methods;
    final private ListDeclField decls;

    public DeclClass(AbstractIdentifier identifier, AbstractIdentifier extension, ListDeclMethod methods, ListDeclField decls) {
        Validate.notNull(identifier);
        Validate.notNull(extension);
        Validate.notNull(methods);
        Validate.notNull(decls);
        this.identifier = identifier;
        this.extension = extension;
        this.methods = methods;
        this.decls = decls;
    }

    public AbstractIdentifier getExtension() {
        return extension;
    }

    public AbstractIdentifier getIdentifier() {
        return identifier;
    }

    public ListDeclMethod getMethods() {
        return methods;
    }

    public ListDeclField getDecls() {
        return decls;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class ");
        identifier.decompile(s);
        s.print(" ");
        extension.decompile(s);
        s.print(" ");
        decls.decompile(s);
        s.print(" ");
        methods.decompile(s);
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }


    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        identifier.prettyPrint(s, prefix, false);
        extension.prettyPrint(s, prefix, false);
        decls.prettyPrintChildren(s, prefix);
        methods.prettyPrintChildren(s, prefix);

    }

    @Override
    protected void iterChildren(TreeFunction f) {
        identifier.iterChildren(f);
        extension.iterChildren(f);
        decls.iterChildren(f);
        methods.iterChildren(f);
    }

}
