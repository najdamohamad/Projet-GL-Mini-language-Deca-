package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGenDisplay;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Expression, i.e. anything that has a value.
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractExpr extends AbstractInst implements CodeGenDisplay {
    /**
     * @return true if the expression does not correspond to any concrete token
     * in the source code (and should be decompiled to the empty string).
     */
    boolean isImplicit() {
        return false;
    }

    /**
     * Get the type decoration associated to this expression (i.e. the type computed by contextual verification).
     */
    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        Validate.notNull(type);
        this.type = type;
    }

    private Type type;

    public DVal getDVal() {
        return null;
    }

    public boolean isFloat() {
        return type.isFloat();
    }

    @Override
    protected void checkDecoration() {
        if (getType() == null) {
            throw new DecacInternalError("Expression " + decompile() + " has no Type decoration");
        }
    }

    /**
     * Verify the expression for contextual error.
     * <p>
     * implements non-terminals "expr" and "lvalue"
     * of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     (contains the "env_types" attribute)
     * @param localEnv     Environment in which the expression should be checked
     *                     (corresponds to the "env_exp" attribute)
     * @param currentClass Definition of the class containing the expression
     *                     (corresponds to the "class" attribute)
     *                     is null in the main bloc.
     * @return the Type of the expression
     * (corresponds to the "type" attribute)
     */
    public abstract Type verifyExpr(DecacCompiler compiler,
                                    EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError;

    /**
     * Verify the expression in right hand-side of (implicit) assignments
     * <p>
     * implements non-terminal "rvalue" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains the "env_types" attribute
     * @param localEnv     corresponds to the "env_exp" attribute
     * @param currentClass corresponds to the "class" attribute
     * @param expectedType corresponds to the "type1" attribute
     * @return this with an additional ConvFloat if needed...
     */
    public AbstractExpr verifyRValue(DecacCompiler compiler,
                                     EnvironmentExp localEnv, ClassDefinition currentClass,
                                     Type expectedType)
            throws ContextualError {
        Type exprType = verifyExpr(compiler, localEnv, currentClass);
        if (!Context.assignCompatible(compiler, expectedType, exprType)) {
            String message = "TypeError: type incorrect pour expression `"
                    + this.decompile() + "`, attendu `" + expectedType
                    + "` mais trouvé `" + exprType + "`.";
            throw new ContextualError(message, getLocation());
        }
        if (exprType.isInt() && expectedType.isFloat()) {
            ConvFloat convFloat = new ConvFloat(this);
            convFloat.setType(compiler.getType("float"));
            return convFloat;
        }
        setType(exprType);
        return this;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
                              ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        verifyExpr(compiler, localEnv, currentClass);
    }

    /**
     * Verify the expression as a condition, i.e. check that the type is
     * boolean.
     *
     * @param localEnv     Environment in which the condition should be checked.
     * @param currentClass Definition of the class containing the expression, or null in
     *                     the main program.
     */
    void verifyCondition(DecacCompiler compiler, EnvironmentExp localEnv,
                         ClassDefinition currentClass) throws ContextualError {
        Type exprType = verifyExpr(compiler, localEnv, currentClass);
        if (!exprType.isBoolean()) {
            String message = "TypeError: type incorrect pour expression `"
                    + this.decompile() + "`, attendu `boolean`"
                    + " mais trouvé `" + exprType + "`.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    public void codeGenDisplay(IMAProgram program, boolean hexadecimal) {
        if (getType().isString()) {
            codeGen(program); // Address of first char is in R0.

            Label reachedZero = new Label("code.displayStringLoop.condition." + hashCode());
            Label endLabel = new Label("code.displayStringLoop.end." + hashCode());

            // Keep writing all the characters from the Stack until reaching 0.
            program.addLabel(reachedZero);
            // Load the character from its address.
            program.addInstruction(new LOAD(
                    new RegisterOffset(0, program.getMaxUsedRegister()),
                    Register.R1
            ));
            // Exit if the character is '\0' (LOAD sets the code condition, as if we did CMP #0, R1).
            // program.addInstruction(new CMP(new ImmediateInteger(0), Register.R1));
            program.addInstruction(new BEQ(endLabel));
            // Write the character (?) in the register R1.
            program.addInstruction(new WUTF8());
            // Increment the address (R0) to point to the next character.
            program.addInstruction(new LEA(
                    new RegisterOffset(1, program.getMaxUsedRegister()),
                    program.getMaxUsedRegister()
            ));
            program.addInstruction(new BRA(reachedZero));
            program.addLabel(endLabel);
        } else if (getType().isInt()) {
            codeGen(program);
            program.addInstruction(new LOAD(program.getMaxUsedRegister(), Register.R1));
            program.addInstruction(new WINT());
        } else if (getType().isFloat()) {
            codeGen(program);
            program.addInstruction(new LOAD(program.getMaxUsedRegister(), Register.R1));
            program.addInstruction(hexadecimal ? new WFLOATX() : new WFLOAT());
        } else {
            throw new DecacInternalError(decompile() + " is not printable, this is a verification bug.");
        }
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void codeGenDisplay(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void decompileInst(IndentPrintStream s) {
        decompile(s);
        s.print(";");
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Type t = getType();
        if (t != null) {
            s.print(prefix);
            s.print("type: ");
            s.print(t);
            s.println();
        }
    }
}
