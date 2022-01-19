package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.TypeDefinition;
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

    final private AbstractIdentifier className;
    final private AbstractIdentifier superClassName;
    final private ListDeclMethod listDeclMethod;
    final private ListDeclField listDeclField;

    public DeclClass(AbstractIdentifier identifier, AbstractIdentifier extension,
                     ListDeclMethod listDeclMethod, ListDeclField listDeclField) {
        Validate.notNull(identifier);
        Validate.notNull(extension);
        Validate.notNull(listDeclMethod);
        Validate.notNull(listDeclField);
        this.className = identifier;
        this.superClassName = extension;
        this.listDeclMethod = listDeclMethod;
        this.listDeclField = listDeclField;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class { ... A FAIRE ... }");
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
        // This implements rule (1.3) of Pass 1
        TypeDefinition superDefinition = compiler.getTypeDefinition(superClassName.getName());
        if (superDefinition.isClass()) {
            ClassType classType = new ClassType(
                    className.getName(),
                    getLocation(),
                    (ClassDefinition) superDefinition
            );
            ClassDefinition classDefinition = new ClassDefinition(
                    classType,
                    getLocation(),
                    (ClassDefinition) superDefinition
            );
            compiler.declareTypeDefinition(className.getName(), classDefinition);
        } else {
            String message = "TypeError: " + superClassName.decompile() + " n'est pas une classe.";
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
