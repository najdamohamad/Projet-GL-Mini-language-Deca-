package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * List of declarations (e.g. int x; float y,z).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ListDeclField extends TreeList<AbstractDeclField> implements CodeGen {

    /**
     * Implements non-terminal "list_decl_field" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler     contains "env_types" attribute
     * @param superClass   corresponds to the "super" attribute
     * @param currentClass corresponds to the "class" attribute
     * @return corresponds to the "env_exp_r" synthesized environment.
     */
    public EnvironmentExp verifyListDeclField(DecacCompiler compiler,
                                              ClassDefinition currentClass, ClassDefinition superClass)
            throws ContextualError {
        EnvironmentExp fieldEnvironment = new EnvironmentExp(null);
        for (AbstractDeclField declField : getList()) {
            declField.verifyDeclField(compiler, fieldEnvironment, currentClass, superClass);
        }
        return fieldEnvironment;
    }

    /**
     * Implements non-terminal "list_decl_field" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains "env_types" attribute
     * @param currentClass corresponds to the "class" attribute.
     */
    public void verifyListDeclFieldInit(DecacCompiler compiler,
                                        ClassDefinition currentClass)
            throws ContextualError {
        for (AbstractDeclField declField : getList()) {
            declField.verifyDeclFieldInit(compiler, currentClass);
        }
    }

    private static final Logger LOG = Logger.getLogger(Program.class);

    @Override
    public void decompile(IndentPrintStream s) {
        boolean notFirst = false;
        for (Iterator<AbstractDeclField> it = iterator(); it.hasNext(); ) {
            AbstractDeclField decl = it.next();

            if (notFirst) {
                s.println(); // newline
            }
            decl.decompile(s);
            notFirst = true;
        }
    }

    @Override
    public int codeGen(IMAProgram program) {
        return getList().stream().map((AbstractDeclField declField) -> {
            return declField.codeGen(program);
        }).max(Integer::compare).orElse(0);
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}