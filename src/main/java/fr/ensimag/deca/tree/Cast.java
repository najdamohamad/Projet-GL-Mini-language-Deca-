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
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Cast extends AbstractExpr {

    public AbstractExpr getTypeCastedTo() {
        return TypeCastedTo;
    }

    public AbstractExpr getRightOperand() {
        return rightOperand;
    }

    protected void setTypeCastedTo(AbstractExpr TypeCastedTo) {
        Validate.notNull(TypeCastedTo);
        this.TypeCastedTo = TypeCastedTo;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        throw new UnsupportedOperationException("not yet implemented");
    }

    protected void setRightOperand(AbstractExpr rightOperand) {
        Validate.notNull(rightOperand);
        this.rightOperand = rightOperand;
    }

    private AbstractExpr TypeCastedTo;
    private AbstractExpr rightOperand;

    public Cast(AbstractExpr TypeCastedTo,
                AbstractExpr rightOperand) {
        Validate.notNull(TypeCastedTo, "Type casted to cannot be null");
        Validate.notNull(rightOperand, "right operand cannot be null");
        Validate.isTrue(TypeCastedTo != rightOperand, "Sharing subtrees is forbidden");
        this.TypeCastedTo = TypeCastedTo;
        this.rightOperand = rightOperand;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("(");
        getTypeCastedTo().decompile(s);
        s.print(") (");
        getRightOperand().decompile(s);
        s.print(")");
    }

    protected String getOperatorName() {
        return "(";
    }

    ;

    @Override
    protected void iterChildren(TreeFunction f) {
        TypeCastedTo.iter(f);
        rightOperand.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        TypeCastedTo.prettyPrint(s, prefix, false);
        rightOperand.prettyPrint(s, prefix, true);
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

