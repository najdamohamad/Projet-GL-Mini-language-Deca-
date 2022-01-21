package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.RTS;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DeclClass extends AbstractDeclClass {

    private static final Logger LOG = Logger.getLogger(Program.class);

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
        s.print("class ");
        className.decompile(s);
        s.print(" ");
        superClassName.decompile(s);
        s.print(" ");
        listDeclField.decompile(s);
        s.print(" ");
        listDeclMethod.decompile(s);
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
        // This implements rule (1.3) of Pass 1
        LOG.debug("begin verifyClass");
        TypeDefinition superDefinition = compiler.getTypeDefinition(superClassName.getName());
        LOG.debug("super class = " + superClassName.getName());
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
            className.setDefinition(classDefinition);
            compiler.declareTypeDefinition(className.getName(), classDefinition);
        } else {
            String message = "TypeError: " + superClassName.decompile() + " n'est pas une classe.";
            throw new ContextualError(message, getLocation());
        }
        LOG.debug("end verifyClass");
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        LOG.debug("start verifying the members of class " + className.getName());
        ClassDefinition classDefinition =
                (ClassDefinition) compiler.getTypeDefinition(className.getName());
        if (superClassName.getName().equals(compiler.createSymbol("Object"))) {
            superClassName.setDefinition(compiler.getTypeDefinition("Object"));
        }
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
        LOG.debug("end verifying the members of class " + className.getName());
    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        LOG.debug("begin ");
        //  On construit un environnement qui contient les champs et les méthodes,
        //  ainsi que les paramètres des méthodes et les variables locales.
        ClassDefinition classDefinition =
                (ClassDefinition) compiler.getTypeDefinition(className.getName());
        listDeclField.verifyListDeclFieldInit(compiler, classDefinition);
        listDeclMethod.verifyListDeclMethodBody(compiler, classDefinition);
    }


    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        className.prettyPrint(s, prefix, false);
        printNodeLine(s, prefix, false, false, "extends");
        superClassName.prettyPrint(s, prefix, false);
        listDeclField.prettyPrintChildren(s, prefix);
        listDeclMethod.prettyPrintChildren(s, prefix);

    }

    @Override
    protected void iterChildren(TreeFunction f) {
        className.iterChildren(f);
        superClassName.iterChildren(f);
        listDeclField.iterChildren(f);
        listDeclMethod.iterChildren(f);
    }

    /**
     * The class initialization must follow the order described in p216, 4.3:
     * - initialize all of our fields to 0.
     * - call initializer for superclass.
     * - use the explicit initialization if present.
     * @param program Abstract representation of the IMA assembly code.
     */
    @Override
    public void codeGen(IMAProgram program) {
        LOG.debug("codegen "+className);
        program.addLabel(new Label("init."+className));
        // Init table method
        for (AbstractDeclMethod method : listDeclMethod.getList()) {
            int position = method.codeGen(program);
        }
        // Init our fields to 0.
        for (AbstractDeclField declField : listDeclField.getList()) {
            LOG.trace("init "+declField+" to 0");
            declField.codeGenInitFieldsZero(program);
        }
        // TODO: init the inherited fields

        // For any fields with any explicit initialization, initialize them now.
        for (AbstractDeclField declField : listDeclField.getList()) {
            LOG.trace("maybe init "+declField+" with initialization");
            declField.codeGen(program);
        }
        program.addInstruction(new RTS());
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
