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
public class MethodAsmBody extends AbstractMethodBody {

    final private String text;

    public MethodAsmBody(String text) {
        Validate.notNull(text);
        this.text = text;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(text);
        s.print(" ");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    }

    @Override
    protected void verifyMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,
                                    ClassDefinition currentClass, Type returnType) throws ContextualError {
        // Nothing to do, good luck!
    }
}