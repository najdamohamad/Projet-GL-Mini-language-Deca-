package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.SOV;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.NotImplementedException;
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

    // bad code, should find a way to use this instead
    @Override
    public void codeGen(IMAProgram program) {
        throw new NotImplementedException("use codeGen with ListDeclVar");
    }

    @Override
    public void codeGen(IMAProgram program, ListDeclVar decls) {
        // Put the address of the variable in VariableDefinition.operand of varName.
        program.addComment(getLocation().getLine() + ": "+decompile());

        DAddr varAddr = new RegisterOffset(program.bumpStackUsage(), Register.GB);
        // Optimisation
        // If we have some free registers, use them instead of the stack.
        // Because of the linking convention, we'll still initialize the stack at the end with the variables.
        // See ListDeclVar.initStack() for how we do this.
        if (!program.isMaxUsableRegister()) {
            GPRegister reg = program.getMaxUsedRegister();
            LOG.trace("decl var, allocated reg="+reg);
            VariableDefinition varDef = varName.getVariableDefinition();
            varDef.setAdress(varAddr);
            varDef.setRegister(reg);
            decls.addGlobalVariableRegister(reg, varAddr);
        } else {
            varName.getVariableDefinition().setAdress(varAddr);
        }
        program.incrementDeclaredVariables();

        // Optimisation
        // TODO: this code can probably be refactored in Initialization.
        if (initialization instanceof Initialization
                && ((Initialization) initialization).getExpression().getDVal() != null
                && Objects.equals(((Initialization) initialization).getExpression().getDVal(), new ImmediateInteger(0))) {
            // Instead of:   LOAD #0, R2
            // use       :   SOV R2
            // OV flag is guantreed to be put to 0 before this instruction,
            // since it should never trigger in a well-formed Decac program. (If it does trigger, we go to our error handler).
            // This saves 2 cycles over loading directly.

            // Store directly into the reg if possible.
            if (varName.getVariableDefinition().isRegister()) {
                program.addInstruction(new SOV(varName.getVariableDefinition().getRegister()), varName.decompile()+" is initialized to 0, using SOV");
            } else {
                program.addInstruction(new SOV(program.getMaxUsedRegister()), varName.decompile()+" is initialized to 0, using SOV");
                program.addInstruction(new STORE(program.getMaxUsedRegister(), varAddr));
            }
        } else if (initialization instanceof Initialization
                && ((Initialization) initialization).getExpression().getDVal() != null
                && varName.getVariableDefinition().isRegister()) {
            // Optimisation: load the dval directly
            DVal dval = ((Initialization) initialization).getExpression().getDVal();
            program.addInstruction(new LOAD(dval, varName.getVariableDefinition().getRegister()));
        } else {
            initialization.codeGen(program);

            if (varName.getVariableDefinition().isRegister()) {
                new LOAD(program.getMaxUsedRegister(), varName.getVariableDefinition().getRegister());
            } else {
                program.addInstruction(new STORE(program.getMaxUsedRegister(), varAddr));
            }
        }

        // Allocate the register only at the end.
        // This is done so that operations that use the max free register, like AbstractOpArith,
        // put the results into the right register (when computing the operation).
        if (varName.getVariableDefinition().isRegister()) {
            program.allocateRegister();
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
