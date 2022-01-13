package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

/**
 * List of expressions (eg list of parameters).
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ListIfThen extends TreeList<AbstractInst> {

    @Override
    public void decompile(IndentPrintStream s) {
        boolean first = true;
        for (AbstractInst e : getList()) {
            if (!first) {
                s.print(",");
            }
            e.decompile(s);
            first = false;
        }
    }
    public void verifyListInst(DecacCompiler compiler, EnvironmentExp localEnv,
                               ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        for (AbstractInst inst : getList()) {
            inst.verifyInst(compiler, localEnv, currentClass, returnType);
        }
    }
}