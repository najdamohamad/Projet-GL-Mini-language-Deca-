package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;
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
        String message = "TypeError: " + superClassName.decompile() + " n'est pas une classe.";
        ClassType superType =
                superClassName.verifyType(compiler).asClassType(message, getLocation());
        ClassType classType =
                new ClassType(className.getName(), getLocation(), superType.getDefinition());
        className.setDefinition(classType.getDefinition());
        try {
            compiler.declareTypeDefinition(className.getName(), classType.getDefinition());
        } catch (EnvironmentType.DoubleDefException e) {
            message = "ScopeError: tentative de définir la classe `" + className.decompile() + "` deux fois.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        LOG.debug("start verifying the members of class " + className.getName());
        ClassDefinition classDefinition = className.getClassDefinition();
        ClassDefinition superDefinition = superClassName.getClassDefinition();
        try {
            // "En pratique, une implémentation pourra simplement ajouter les nouvelles définitions
            // à l’environnement contenu dans la définition de classe construite en passe 1.
            // Il n’est pas nécessaire de créer une nouvelle définition de classe et l’empilement
            // d’environnement peut être fait dès la création de la définition de classe en passe 1."
            //     -- Page 81, règle (2.3)
            EnvironmentExp fieldEnvironment = listDeclField.verifyListDeclField(
                    compiler, classDefinition, superDefinition
            );
            classDefinition.getMembers().join(fieldEnvironment);
            EnvironmentExp methodEnvironment = listDeclMethod.verifyListDeclMethod(
                    compiler, classDefinition, superDefinition
            );
            classDefinition.getMembers().join(methodEnvironment);

        } catch (EnvironmentExp.DoubleDefException e) {
            // FIXME: the only evidence for this is page 77 where it says the OPLUS operator
            //        is not defined when the intersection of environment is non-empty.
            String message = "ScopeError: il n'est pas possible d'utiliser le même identificateur" +
                    "pour un champ et une méthode.";
            throw new ContextualError(message, getLocation());
        }

        // Set the proper number of fields for this class,
        // taking into account the superclass fields.
        // See the example page 185.
        classDefinition.setNumberOfFields(classDefinition.getNumberOfFields() + superDefinition.getNumberOfFields());
        // Same for methods.
        classDefinition.setNumberOfMethods(classDefinition.getNumberOfMethods() + superDefinition.getNumberOfMethods());

        LOG.debug("end verifying the members of class " + className.getName());
    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        LOG.debug("begin ");
        //  On construit un environnement qui contient les champs et les méthodes,
        //  ainsi que les paramètres des méthodes et les variables locales.
        ClassDefinition classDefinition = className.getClassDefinition();
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
     *
     * @param program Abstract representation of the IMA assembly code.
     */
    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = 0;
        IMAProgram programInit = new IMAProgram(program);
        LOG.debug("codegen "+className);
//        DAddr position = new RegisterOffset(programInit.getStackUsage() + 1, Register.GB);
//        className.getClassDefinition().setMethodTableAddr(position);
//        int placeDansLeStack = 1;
//
//        DAddr positionMere;
//        if (superClassName.getClassDefinition().isClass()) {
//            positionMere = superClassName.getClassDefinition().getMethodTableAddr();
//        } else {
//            positionMere = new RegisterOffset(0, Register.GB);
//        }
//
//        programInit.addInstruction(new LEA(positionMere, Register.R0));
//        programInit.addInstruction(new STORE(Register.R0, position));
//        programInit.bumpStackUsage();
//        for (AbstractDeclMethod method : listDeclMethod.getList()) {
//            className.getClassDefinition().listMethod.add(method.getMethodName().getName().toString());
//        }
//        // Init table method inherited
//        for (String SuperMethodName : superClassName.getClassDefinition().listMethod){
//            if (!className.getClassDefinition().listMethod.contains(SuperMethodName)){
//                programInit.addInstruction(new LOAD(new LabelOperand(new Label(SuperMethodName)), Register.R0));
//                programInit.addInstruction(new STORE(Register.R0, new RegisterOffset(placeDansLeStack, Register.GB)));
//                programInit.bumpStackUsage();
//                className.getClassDefinition().listMethod.add(SuperMethodName);
//                placeDansLeStack += 1;
//            }
//        }
//        // Init table method
//        for (AbstractDeclMethod method : listDeclMethod.getList()) {
//            placeDansLeStack = method.codeGenInitTable(programInit, placeDansLeStack);
//            programInit.bumpStackUsage();
//        }
        // Init our fields to 0.
        for (AbstractDeclField declField : listDeclField.getList()) {
            LOG.trace("init " + declField + " to 0");
            declField.codeGenInitFieldsZero(programInit);
        }

        // If there is a superclass to initialize, do it.
        // Note that Object has no initializer, so skip it if the superclass is Object.
            if (superClassName.getClassDefinition().isClass()
                && !superClassName.getClassDefinition().getType().toString().equals("Object")) {
            stackUsage += 1;
            programInit.addInstruction(new PUSH(Register.R1));
            programInit.addInstruction(new BSR(new Label("init." + superClassName)));
            programInit.addInstruction(new SUBSP(new ImmediateInteger(1)));
        }

        // For any fields with any explicit initialization, initialize them now.
        stackUsage += listDeclField.getList().stream().map((AbstractDeclField declField) -> {
            LOG.trace("maybe init " + declField + " with initialization");
            return declField.codeGen(programInit);
        }).max(Integer::compare).orElse(0);


        stackUsage += programInit.generatePrologueEpilogue();
        if (stackUsage > 0) {
            // addFirst -> put operations in reverse order
            programInit.addFirst(new BOV(Program.STACK_OVERFLOW_ERROR));
            programInit.addFirst(new TSTO(new ImmediateInteger(stackUsage)));

        } else {
            programInit.addComment("stack usage is 0, no TSTO added");
        }
        programInit.addFirst(new Label("init." + className));
        program.append(programInit);
        return stackUsage;
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
