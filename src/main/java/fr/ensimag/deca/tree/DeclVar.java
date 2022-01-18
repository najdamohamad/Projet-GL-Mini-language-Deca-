package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclVar extends AbstractDeclVar {


    final private AbstractIdentifier type;
    final private AbstractIdentifier varName;
    final private AbstractInitialization initialization;

    public DeclVar(AbstractIdentifier type, AbstractIdentifier varName, AbstractInitialization initialization) {
        Validate.notNull(type);
        Validate.notNull(varName);
        Validate.notNull(initialization);
        this.type = type;
        this.varName = varName;
        this.initialization = initialization;
    }

    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
                                 EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type varType = type.verifyType(compiler);
        if (varType.isVoid()) { // JACOCO should use isVoid()--sameType(compiler.getType("void"))
            String message = "TypeError: il est impossible de déclarer des identificateurs de type `void`.";
            throw new ContextualError(message, getLocation());
        }
        try {
            ExpDefinition varDefinition = new VariableDefinition(varType, getLocation());
            varName.setDefinition(varDefinition);
            localEnv.declare(varName.getName(), varDefinition);
            initialization.verifyInitialization(compiler, varType, localEnv, currentClass);
        } catch (EnvironmentExp.DoubleDefException e) {
            String message = "ScopeError: l'identificateur `"
                    + varName.decompile() + "` ne peut être défini plus qu'une fois.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    public void codeGen(IMAProgram program) {
        // Put the address of the variable in VariableDefinition.operand of varName.
        DAddr varAddr = new RegisterOffset(program.bumpStackUsage(), Register.GB);
        program.addInstruction(new ADDSP(new ImmediateInteger(1)));
        varName.getVariableDefinition().setOperand(varAddr);
        // This will put the result of calculating the expression in max used register.
        initialization.codeGen(program);
        if (initialization instanceof Initialization) {
            program.addInstruction(new STORE(program.getMaxUsedRegister(), varAddr));
        }
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        varName.decompile(s);
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
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }
}
