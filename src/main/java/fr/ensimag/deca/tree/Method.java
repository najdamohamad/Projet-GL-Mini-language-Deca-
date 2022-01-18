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

/**
 * Method
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Method extends AbstractExpr {
    private AbstractExpr expression;
    private AbstractIdentifier method;
    private ListExpr list_params;

    public Method(AbstractExpr expression, AbstractIdentifier method, ListExpr list_params) {
        Validate.notNull(expression);
        Validate.notNull(method);
        Validate.notNull(list_params);
        this.expression =expression;
        this.method = method;
        this.list_params = list_params;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }

    @Override
    public void codeGenDisplay(IMAProgram program) {
    }

    public void codeGenExpr(IMAProgram program,GPRegister register) {
    }

    @Override
    public void decompile(IndentPrintStream s) {
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    }

}