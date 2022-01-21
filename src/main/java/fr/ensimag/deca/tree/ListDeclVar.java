package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * List of declarations (e.g. int x; float y,z).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ListDeclVar extends TreeList<AbstractDeclVar> implements CodeGen {
    private static final Logger LOG = Logger.getLogger(Program.class);

    /**
     * A mapping from variable names to adresses on the stack.
     * This will be used to write any global variables that have been allocated into registers
     * to the stack at the end.
     */
    private final Map<GPRegister, DAddr> registerToAdress = new HashMap<>();

    public void addGlobalVariableRegister(GPRegister reg, DAddr addr) {
        Validate.notNull(reg);
        Validate.notNull(addr);
        registerToAdress.put(reg, addr);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        boolean notFirst = false;
        for (Iterator<AbstractDeclVar> it = iterator(); it.hasNext(); ) {
            AbstractDeclVar decl = it.next();

            if (notFirst) {
                s.println(); // newline
            }
            decl.decompile(s);
            notFirst = true;
        }
    }

    /**
     * Implements non-terminal "list_decl_var" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains the "env_types" attribute
     * @param localEnv     its "parentEnvironment" corresponds to "env_exp_sup" attribute
     *                     in precondition, its "current" dictionary corresponds to
     *                     the "env_exp" attribute
     *                     in postcondition, its "current" dictionary corresponds to
     *                     the "env_exp_r" attribute
     * @param currentClass corresponds to "class" attribute (null in the main bloc).
     */
    void verifyListDeclVariable(DecacCompiler compiler, EnvironmentExp localEnv,
                                ClassDefinition currentClass) throws ContextualError {
        for (AbstractDeclVar declVar : getList()) {
            declVar.verifyDeclVar(compiler, localEnv, currentClass);
        }
    }

    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = getList().stream().map((AbstractDeclVar d) -> {
            return d.codeGen(program, this);
        }).max(Integer::compare).orElse(0);
        // Now that all variables have been declared, increment SP in one shot.
        program.addInstruction(new ADDSP(new ImmediateInteger(program.getDeclaredVariablesCount())), program.getDeclaredVariablesCount()+" variables declared");
        return stackUsage;
    }

    /**
     * This epilogue pushes every global variable that has been allocated to a register on the stack.
     */
    public void epilogue(IMAProgram program) {
        program.addComment("loading global variables from register to stack, to respect calling convention");
        for (Map.Entry<GPRegister, DAddr> entry : registerToAdress.entrySet()) {
            program.addInstruction(new STORE(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }


}
