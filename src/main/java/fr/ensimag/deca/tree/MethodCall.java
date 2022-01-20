package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.arm.pseudocode.Assign;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WINT;
import org.apache.commons.lang.Validate;
import fr.ensimag.ima.pseudocode.DVal;
import java.io.PrintStream;
import fr.ensimag.arm.pseudocode.ARMProgram;
/**
 * Method
 *
 * @author gl47
 * @date 01/01/2022
 */
public class MethodCall extends AbstractExpr {
    private AbstractExpr expression;
    private AbstractIdentifier method;
    private ListExpr listArgs;

    public MethodCall(AbstractExpr expression, AbstractIdentifier method, ListExpr listArgs) {
        Validate.notNull(expression);
        Validate.notNull(method);
        Validate.notNull(listArgs);
        this.expression =expression;
        this.method = method;
        this.listArgs = listArgs;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }


    public void codeGenExpr(IMAProgram program,GPRegister register) {
    }

    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print(".");
        method.decompile(s);
        s.print("(");
        listArgs.decompile(s);
        s.print(")");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iterChildren(f);
        method.iterChildren(f);
        listArgs.iterChildren(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrintChildren(s, prefix);
        method.prettyPrintChildren(s, prefix);
        listArgs.prettyPrintChildren(s, prefix);
    }

    @Override
    public void codeGen(ARMProgram program){}
    @Override
    public void codeGen(IMAProgram program){}
}