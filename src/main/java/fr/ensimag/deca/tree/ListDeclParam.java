package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * List of declarations (e.g. int x; float y,z).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ListDeclParam extends TreeList<DeclParam> {
    /**
     * Implements non-terminal "decl_param" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler contains "env_types" attribute
     */
    protected Signature verifyDeclParamType(DecacCompiler compiler) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Implements non-terminal "decl_param" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler contains "env_types" attribute
     * @param localEnv corresponds to the "env_exp_r" attribute
     */
    protected void verifyDeclParam(DecacCompiler compiler, EnvironmentExp localEnv) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    private static final Logger LOG = Logger.getLogger(Program.class);

    @Override
    public void decompile(IndentPrintStream s) {
        boolean notFirst = false;
        for (Iterator<DeclParam> it = iterator(); it.hasNext(); ) {
            DeclParam param = it.next();

            if (notFirst) {
                s.println(); // newline
            }
            param.decompile(s);
            notFirst = true;
        }
    }

}