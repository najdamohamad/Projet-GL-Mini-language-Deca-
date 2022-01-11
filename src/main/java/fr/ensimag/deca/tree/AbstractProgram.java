package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.ima.pseudocode.IMAProgram;

/**
 * Entry point for contextual verifications and code generation from outside the package.
 * 
 * @author gl47
 * @date 01/01/2022
 *
 */
public abstract class AbstractProgram extends Tree implements CodeGen {
    public abstract void verifyProgram(DecacCompiler compiler) throws ContextualError;
}
