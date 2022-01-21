package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.NotImplementedException;

/**
 * Variable declaration
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractDeclField extends Tree implements CodeGen {

    /**
     * Implements non-terminal "decl_method" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler     contains "env_types" attribute
     * @param localEnv     corresponds to the "env_exp" of field declarations
     * @param superClass   corresponds to the "super" attribute
     * @param currentClass corresponds to the "class" attribute
     */
    protected abstract void verifyDeclField(DecacCompiler compiler, EnvironmentExp localEnv,
                                            ClassDefinition currentClass, ClassDefinition superClass)
            throws ContextualError;

    /**
     * Implements non-terminal "decl_method" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains "env_types" attribute
     * @param currentClass corresponds to the "class" attribute (null in the main bloc).
     */
    protected abstract void verifyDeclFieldInit(DecacCompiler compiler,
                                                ClassDefinition currentClass)
            throws ContextualError;

    public abstract void codeGenInitFieldsZero(IMAProgram programInit, int fieldOffset);

    public abstract int codeGen(IMAProgram programInit, int fieldOffset);

    @Override
    public int codeGen(IMAProgram program) {
        throw new NotImplementedException("should not be implemented, use codeGen(programInit, fieldOffset)");
    }
}
