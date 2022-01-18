package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractMethodBody extends AbstractExpr {



    @Override
    public void decompile(IndentPrintStream s) {
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    }

}
