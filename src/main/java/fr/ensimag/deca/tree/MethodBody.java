package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class MethodBody extends AbstractMethodBody {

    final private ListDeclVar decls;
    final private ListInst insts;

    public MethodBody(ListDeclVar decls, ListInst insts) {
        Validate.notNull(decls);
        Validate.notNull(insts);
        this.decls = decls;
        this.insts = insts;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        decls.decompile(s);
        s.print(" ");
        insts.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        decls.iter(f);
        insts.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        decls.prettyPrint(s, prefix, false);
        insts.prettyPrint(s, prefix, false);
    }

    @Override
    protected void verifyMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,
                                    ClassDefinition currentClass, Type returnType) throws ContextualError {
        // TODO: when there were no classes, we simply ignored the members of the currectClass,
        //       now everything should be checked to account for it.
        decls.verifyListDeclVariable(compiler, localEnv, currentClass);
        insts.verifyListInst(compiler, localEnv, currentClass, returnType);
    }
}
