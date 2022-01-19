package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.SOV;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.util.Objects;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclVar extends AbstractDeclVar {
    private static final Logger LOG = Logger.getLogger(DeclVar.class);

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
        if (varType.sameType(compiler.getType("void"))) {
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
        program.addComment(getLocation().getLine() + ": "+decompile());
        DAddr varAddr = new RegisterOffset(program.bumpStackUsage(), Register.GB);
        program.incrementDeclaredVariables();
        varName.getVariableDefinition().setOperand(varAddr);

        // Optimisation
        if (initialization instanceof Initialization
                && ((Initialization) initialization).getExpression().getDVal() != null
                && Objects.equals(((Initialization) initialization).getExpression().getDVal(), new ImmediateInteger(0))) {
            // Instead of:   LOAD #0, R2
            // use       :   SOV R2
            // OV flag is guantreed to be put to 0 before this instruction,
            // since it should never trigger in a well-formed Decac program. (If it does trigger, we go to our error handler).
            // This saves 2 cycles over loading directly.
            program.addInstruction(new SOV(program.getMaxUsedRegister()));
        } else {
            // This will put the result of calculating the expression in max used register.
            initialization.codeGen(program);
        }

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
