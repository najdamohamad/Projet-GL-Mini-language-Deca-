package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class New extends AbstractExpr {

    public AbstractIdentifier getClassName() {
        return className;
    }

    protected void setClassName(AbstractIdentifier className) {
        Validate.notNull(className);
        this.className = className;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type exprType = className.verifyType(compiler);
        if (!exprType.isClass()) {
            String message = "TypeError: impossible d'instancier `"
                    + className.decompile() + "`ce n'est pas une classe.";
            throw new ContextualError(message, getLocation());
        }
        setType(exprType);
        return exprType;
    }

    private AbstractIdentifier className;

    public New(AbstractIdentifier className) {
        Validate.notNull(className, "ident cannot be null");
        this.className = className;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("new ");
        getClassName().decompile(s);
        s.print("()");
    }

    protected String getOperatorName() {
        return "new ()";
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        className.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        className.prettyPrint(s, prefix, false);
    }

    /**
     * Algorithm 7.4 p223 pour new
     */
    @Override
    public int codeGen(IMAProgram program) {
        // NEW #d, R2
        int objectSize = getClassName().getClassDefinition().getNumberOfFieldsAndSuperclassFields() + 1;
        program.addInstruction(new NEW(new ImmediateInteger(objectSize), program.getMaxUsedRegister()),
                getClassName().getClassDefinition().getNumberOfFieldsAndSuperclassFields()  + " fields for " + getClassName());

        if (program.shouldCheck()) {
            // BOV tas_plein
            program.addInstruction(new BOV(Program.STACK_OVERFLOW_ERROR));
        }
        // TODO: lea adress of method table
        // LEA ad_A, R0
        // STORE RO, 0(R2)
        // We may try to init a object.
        if (!getClassName().getClassDefinition().getType().toString().equals("Object")) {
            program.addInstruction(new PUSH(program.getMaxUsedRegister()));
            program.addInstruction(new BSR(new Label("init." + getClassName())));
            program.addInstruction(new POP(program.getMaxUsedRegister()));
        }
        return 1; // 1 stack used because of push
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}