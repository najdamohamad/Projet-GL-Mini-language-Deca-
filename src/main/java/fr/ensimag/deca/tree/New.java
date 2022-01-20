package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
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

    @Override
    public void codeGen(IMAProgram program) {
        ClassDefinition definition =
                (ClassDefinition) program.getCompiler().getTypeDefinition(className.getName());
        int objectSize = definition.getNumberOfFields();
        program.addInstruction(new NEW(objectSize, program.getMaxUsedRegister()));
        program.addInstruction(new BOV(Program.HEAP_OVERFLOW_ERROR));
        // TODO: store the actual method table address.
        // program.addInstruction(new LEA(..., Register.R0));
        program.addInstruction(new STORE(
                Register.R0,
                new RegisterOffset(0, program.getMaxUsedRegister()))
        );
        program.addInstruction(new PUSH(program.getMaxUsedRegister()));
        program.addInstruction(new BSR(
                new LabelOperand(new Label("init." + className.getName()))
        ));
        program.addInstruction(new PUSH(program.getMaxUsedRegister()));
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}