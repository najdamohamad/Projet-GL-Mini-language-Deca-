package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
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
        String message = "type error" + TypeCastedTo.decompile() + "n'est pas int ou float ou une class";
        Type typeCastedToType = TypeCastedTo.verifyExpr(compiler, localEnv, currentClass);
        Type exprType = rightOperand.getType();
        ClassType classType = currentClass.getType();
        if(!(typeCastedToType.isIntOrFloat() || typeCastedToType.isClass())){
            throw new ContextualError(message, getLocation());
        }
        else{
            if(!(exprType.isIntOrFloat() || exprType.isClass())){
                message = "Type: l'expression "+ rightOperand.decompile() + "doit être un int, float ou une class";
                throw new ContextualError(message, getLocation());
            }
            else if (!Context.subType(typeCastedToType, classType)){
                message = "Type: l'expression `" + TypeCastedTo.decompile()
                        + "` doit être un sous type de `" + classType + ".";
                throw new ContextualError(message, getLocation());
            }
            else if (!Context.subType(exprType, typeCastedToType) && !Context.subType(typeCastedToType  , exprType)){
                message = "Type: les deux expressions sont de types incompatible.";
                throw new ContextualError(message, getLocation());
            }
        }
        return typeCastedToType;
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

