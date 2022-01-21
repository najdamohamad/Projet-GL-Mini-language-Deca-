package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
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

    public void codeGenInitFieldsZero(IMAProgram program, int fieldOffset) {
        FieldDefinition field = varName.getFieldDefinition();
        program.addComment("initializing " + field.getContainingClass() + "." + varName + " to 0");
        program.addInstruction(new LOAD(0, Register.R0));
        program.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.R1));
        program.addInstruction(new STORE(
                Register.R0,
                // 0(R1) is adress of method  table, add one
                new RegisterOffset(fieldOffset + field.getIndex() + 1, Register.R1)
        ));
    }

    /**
     * Codegen for a field declaration of a class.
     * @param program The program.
     * @param fieldOffset This field may be declared in an herited class.
     *                    In this case, it doens't start at offset 0, but after
     *                    the herited fields.
     * @return Stack usage.
     */
    public int codeGen(IMAProgram program, int fieldOffset) {
        if (initialization instanceof NoInitialization) {
            return 0;
        }
        LOG.trace("got init, initing field "+this+" with init: "+initialization);
        FieldDefinition field = varName.getFieldDefinition();
        program.addComment("initializing " + field.getContainingClass().getType() + "." + varName
            + " with expression " + initialization);
        int stackUsage = initialization.codeGen(program);
        program.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.R1));
        program.addInstruction(new STORE(
                program.getMaxUsedRegister(),
                // 0(R1) is adress of method  table, add one
                new RegisterOffset(fieldOffset + field.getIndex() + 1, Register.R1)
        ));
        return stackUsage;
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public String toString() {
        return "DeclField("+varName.getName()+")";
    }
}
