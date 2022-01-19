package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Attribut
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Selection extends AbstractLValue {

    final private AbstractExpr expression;
    final private AbstractIdentifier attribute;

    public Selection(AbstractExpr expression, AbstractIdentifier attribut) {
        Validate.notNull(expression);
        Validate.notNull(attribut);
        this.expression = expression;
        this.attribute = attribut;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }


    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        attribute.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}