package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
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
        ClassDefinition classDefinition =
                (ClassDefinition) compiler.getTypeDefinition(className.getName());
        try {
            // "En pratique, une implémentation pourra simplement ajouter les nouvelles définitions
            // à l’environnement contenu dans la définition de classe construite en passe 1.
            // Il n’est pas nécessaire de créer une nouvelle définition de classe et l’empilement
            // d’environnement peut être fait dès la création de la définition de classe en passe 1."
            //     -- Page 81, règle (2.3)
            EnvironmentExp fieldEnvironment = listDeclField.verifyListDeclField(
                    compiler, className.getClassDefinition(), superClassName.getClassDefinition()
            );
            classDefinition.getMembers().join(fieldEnvironment);
            EnvironmentExp methodEnvironment = listDeclMethod.verifyListDeclMethod(
                    compiler, className.getClassDefinition(), superClassName.getClassDefinition()
            );
            classDefinition.getMembers().join(methodEnvironment);

        } catch (EnvironmentExp.DoubleDefException e) {
            // FIXME: the only evidence for this is page 77 where it says the OPLUS operator
            //        is not defined when the intersection of environment is non-empty.
            String message = "ScopeError: il n'est pas possible d'utiliser le même identificateur" +
                    "pour un champ et une méthode.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        //  On construit un environnement qui contient les champs et les méthodes,
        //  ainsi que les paramètres des méthodes et les variables locales.
        ClassDefinition classDefinition =
                (ClassDefinition) compiler.getTypeDefinition(className.getName());
        listDeclField.verifyListDeclFieldInit(compiler, classDefinition);
        listDeclMethod.verifyListDeclMethodBody(compiler, classDefinition);
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
