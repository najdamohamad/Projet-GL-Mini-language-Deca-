package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class ListInst extends TreeList<AbstractInst> implements CodeGen {

    /**
     * Implements non-terminal "list_inst" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains "env_types" attribute
     * @param localEnv     corresponds to "env_exp" attribute
     * @param currentClass corresponds to "class" attribute (null in the main bloc).
     * @param returnType   corresponds to "return" attribute (void in the main bloc).
     */
    public void verifyListInst(DecacCompiler compiler, EnvironmentExp localEnv,
                               ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        for (AbstractInst inst : getList()) {
            inst.verifyInst(compiler, localEnv, currentClass, returnType);
        }
    }

    @Override
    public void codeGen(IMAProgram program) {
        for (AbstractInst i : getList()) {
            i.codeGen(program);
        }
    }

    @Override
    public void codeGen(ARMProgram program) {
        for (AbstractInst i : getList()) {
            i.codeGen(program);
        }
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractInst i : getList()) {
            i.decompileInst(s);
            s.println();
        }
    }
}
