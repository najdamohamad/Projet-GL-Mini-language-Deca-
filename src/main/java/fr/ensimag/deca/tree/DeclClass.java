package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;

import fr.ensimag.deca.tools.SymbolTable;
import org.apache.commons.lang.Validate;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DeclClass extends AbstractDeclClass {
    final private AbstractIdentifier identifier;
    final private AbstractIdentifier extension;
    final private ClassBody classBody;

    public DeclClass(AbstractIdentifier identifier, AbstractIdentifier extension, ClassBody classBody) {
        Validate.notNull(identifier);
        Validate.notNull(extension);
        Validate.notNull(classBody);
        this.identifier = identifier;
        this.extension = extension;
        this.classBody = classBody;
    }

    public AbstractIdentifier getExtension() {
        return extension;
    }

    public AbstractIdentifier getIdentifier() {
        return identifier;
    }

    public ClassBody getClassBody() {
        return classBody;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void verifyClass(DecacCompiler compiler)
            throws ContextualError {
        SymbolTable.Symbol className = identifier.getName();
        SymbolTable.Symbol superClassName = extension.getName();
        TypeDefinition superClass = compiler.getTypeDefinition(superClassName.getName());
        if (superClass.isClass()) {
            ClassType classType = new ClassType(className, getLocation(), (ClassDefinition) superClass);
            compiler.declareTypeDefinition(
                    className,
                    new ClassDefinition(classType, getLocation(), (ClassDefinition) superClass)
            );


                    for (AbstractDeclMethod declMethod : classBody.getListDeclMethod().getList()) {

                        declMethod.ver);

                    }
                    classBody.getListDeclField();
                    classBody.getListDeclMethod();
        } else {
            String message = "Type Error: " + superClassName.getName() + "n'est pas une classe.";
            throw new ContextualError(message, getLocation());
        }
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
        throw new UnsupportedOperationException("Not yet supported");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        throw new UnsupportedOperationException("Not yet supported");
    }

}
