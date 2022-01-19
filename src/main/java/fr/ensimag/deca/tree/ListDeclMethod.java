package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * List of declarations (e.g. int x; float y,z).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ListDeclMethod extends TreeList<AbstractDeclMethod> {
    /**
     * Implements non-terminal "list_decl_method" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler   contains the "env_types" attribute
     * @param superClass corresponds to "super" attribute
     * @return corresponds to the "env_exp_r" attribute
     */
    public EnvironmentExp verifyListDeclMethod(DecacCompiler compiler, EnvironmentExp localEnv,
                                               ClassDefinition superClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Implements non-terminal "list_decl_method" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler     contains the "env_types" attribute
     * @param localEnv     its "parentEnvironment" corresponds to "env_exp_sup" attribute
     *                     in precondition, its "current" dictionary corresponds to
     *                     the "env_exp" attribute
     *                     in postcondition, its "current" dictionary corresponds to
     *                     the "env_exp_r" attribute
     * @param currentClass corresponds to "class" attribute (null in the main bloc).
     */
    void verifyListDeclMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,
                                  ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    private static final Logger LOG = Logger.getLogger(Program.class);

    @Override
    public void decompile(IndentPrintStream s) {
        boolean notFirst = false;
        for (Iterator<AbstractDeclMethod> it = iterator(); it.hasNext(); ) {
            AbstractDeclMethod decl = it.next();

            if (notFirst) {
                s.println(); // newline
            }
            decl.decompile(s);
            notFirst = true;
        }
    }
}