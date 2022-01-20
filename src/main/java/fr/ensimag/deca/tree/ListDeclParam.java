package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
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
public class ListDeclParam extends TreeList<AbstractDeclParam> {
    /**
     * Implements non-terminal "list_decl_param" of [SyntaxeContextuelle] in pass 2
     *
     * @param compiler contains "env_types" attribute
     */
    protected Signature verifyListDeclParamType(DecacCompiler compiler) throws ContextualError {
        Signature signature = new Signature();
        for (AbstractDeclParam declParam : getList()) {
            signature.add(declParam.verifyDeclParamType(compiler));
        }
        return signature;
    }

    /**
     * Implements non-terminal "list_decl_param" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler contains "env_types" attribute
     */
    protected EnvironmentExp verifyListDeclParam(DecacCompiler compiler, ClassDefinition currentClass)
            throws ContextualError {
        EnvironmentExp paramEnvironment = new EnvironmentExp(currentClass.getMembers());
        for (AbstractDeclParam declParam : getList()) {
            declParam.verifyDeclParam(compiler, paramEnvironment);
        }
        return paramEnvironment;
    }

    private static final Logger LOG = Logger.getLogger(Program.class);

    @Override
    public void decompile(IndentPrintStream s) {
        boolean notFirst = false;
        for (Iterator<AbstractDeclParam> it = iterator(); it.hasNext(); ) {
            AbstractDeclParam param = it.next();

            if (notFirst) {
                s.println(); // newline
            }
            param.decompile(s);
            notFirst = true;
        }
    }

}