package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclField extends AbstractDeclField {

    private static final Logger LOG = Logger.getLogger(Identifier.class);

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
        printNodeLine(s, prefix, false, false, "Visibility " + visibility.toString());
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }

    @Override
    protected void verifyDeclField(DecacCompiler compiler, EnvironmentExp localEnv,
                                   ClassDefinition currentClass, ClassDefinition superClass) throws ContextualError {
        Type fieldType = type.verifyType(compiler);
        if (fieldType.isVoid()) {
            String message = "TypeError: un champ ne peut être déclaré avec le type `void`";
            throw new ContextualError(message, getLocation());
        }
        // Si l’identificateur name est déjà défini dans l’environnement
        // des expressions de la super-classe, alors ce doit être un identificateur de champ.
        ExpDefinition superDefinition = superClass.getMembers().get(varName.getName());
        if (superDefinition != null && !superDefinition.isField()) {
            String message = "ScopeError: tentative de redéfinir une méthode en un champ.";
            throw new ContextualError(message, getLocation());
        }
        FieldDefinition fieldDefinition = new FieldDefinition(
                fieldType,
                getLocation(),
                visibility,
                currentClass,
                currentClass.getNumberOfFields()
        );
        varName.setDefinition(fieldDefinition);
        currentClass.incNumberOfFields();
        try {
            localEnv.declare(varName.getName(), fieldDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            String message = "ScopeError: tentative de définir le champ `"
                    + varName.decompile() + "` deux fois.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    protected void verifyDeclFieldInit(DecacCompiler compiler,
                                       ClassDefinition currentClass) throws ContextualError {
        Type fieldType = type.verifyType(compiler);
        initialization.verifyInitialization(compiler, fieldType, currentClass.getMembers(), currentClass);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    @Override
    public void codeGenInitFieldsZero(IMAProgram program) {
        FieldDefinition field = varName.getFieldDefinition();
        program.addComment("initializing " + field.getContainingClass() + "." + varName + "to 0");
        initialization.codeGen(program);
        program.addInstruction(new LOAD(0, program.getMaxUsedRegister()));
        program.addInstruction(new STORE(
                program.getMaxUsedRegister(),
                new RegisterOffset(field.getIndex(), Register.R1)
        ));
    }

    @Override
    public void codeGen(IMAProgram program) {
        FieldDefinition field = varName.getFieldDefinition();
        program.addComment("initializing " + field.getContainingClass() + "." + varName);
        initialization.codeGen(program);
        program.addInstruction(new STORE(
                program.getMaxUsedRegister(),
                new RegisterOffset(field.getIndex(), Register.R1)
        ));
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
