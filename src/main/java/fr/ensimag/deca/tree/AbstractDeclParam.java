package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

public abstract class AbstractDeclParam extends Tree {
    /**
     * Implements non-terminal "decl_param" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler contains "env_types" attribute
     */
    protected abstract void verifyDeclParamType(DecacCompiler compiler) throws ContextualError;

    /**
     * Implements non-terminal "decl_param" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler contains "env_types" attribute
     * @param localEnv corresponds to the "env_exp_r" attribute
     */
    protected abstract void verifyDeclParam(DecacCompiler compiler, EnvironmentExp localEnv) throws ContextualError;
}
