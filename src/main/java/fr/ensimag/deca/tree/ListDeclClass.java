package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.log4j.Logger;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class ListDeclClass extends TreeList<AbstractDeclClass> implements CodeGen {
    private static final Logger LOG = Logger.getLogger(ListDeclClass.class);

    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclClass c : getList()) {
            c.decompile(s);
            s.println();
        }
    }

    /**
     * Pass 1 of [SyntaxeContextuelle]
     */
    void verifyListClass(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify listClass: start");
        for (AbstractDeclClass declClass : getList()) {
            declClass.verifyClass(compiler);
        }
        LOG.debug("verify listClass: end");
    }

    /**
     * Pass 2 of [SyntaxeContextuelle]
     */
    public void verifyListClassMembers(DecacCompiler compiler) throws ContextualError {
        for (AbstractDeclClass declClass : getList()) {
            declClass.verifyClassMembers(compiler);
        }
    }

    /**
     * Pass 3 of [SyntaxeContextuelle]
     */
    public void verifyListClassBody(DecacCompiler compiler) throws ContextualError {
        for (AbstractDeclClass declClass : getList()) {
            declClass.verifyClassBody(compiler);
        }
    }

    @Override
    public int codeGen(IMAProgram program) {
        return getList().stream().map((AbstractDeclClass declClass) -> {
            return declClass.codeGen(program);
        }).max(Integer::compare).orElse(0);
    }

    public int codeGenMethodTable(IMAProgram program) {
        // Hardcoded: method table for object.
        program.addInstruction(new LOAD(new NullOperand(), Register.R0));
        program.addInstruction(new STORE(Register.R0, new RegisterOffset(1, Register.GB)));
        program.addInstruction(new LOAD(new LabelOperand(new Label("code.Object.equals")), Register.R0));
        program.addInstruction(new STORE(Register.R0, new RegisterOffset(2, Register.GB)));

        int sum = 0;
        for (AbstractDeclClass declClass: getList()) {
            sum += declClass.codeGenMethodTable(program);
        }
        return sum + 2; // 2 for load+store of object
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
