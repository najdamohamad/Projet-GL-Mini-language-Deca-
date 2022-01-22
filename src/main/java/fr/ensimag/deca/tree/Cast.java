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

    public Cast(AbstractIdentifier leftOperand, AbstractExpr rightOperand) {
        Validate.notNull(leftOperand);
        Validate.notNull(rightOperand);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        String message = "type error" + leftOperand.decompile() + "n'est pas int ou float ou une class";
        Type typeCastedToType = leftOperand.verifyExpr(compiler, localEnv, currentClass);
        Type exprType = rightOperand.getType();
        ClassType classType = currentClass.getType();
        if (!(typeCastedToType.isIntOrFloat() || typeCastedToType.isClass())) {
            throw new ContextualError(message, getLocation());
        } else {
            if (!(exprType.isIntOrFloat() || exprType.isClass())) {
                message = "Type: l'expression " + rightOperand.decompile() + "doit être un int, float ou une class";
                throw new ContextualError(message, getLocation());
            } else if (!Context.subType(typeCastedToType, classType)) {
                message = "Type: l'expression `" + leftOperand.decompile()
                        + "` doit être un sous type de `" + classType + ".";
                throw new ContextualError(message, getLocation());
            } else if (!Context.subType(exprType, typeCastedToType) && !Context.subType(typeCastedToType, exprType)) {
                message = "Type: les deux expressions sont de types incompatible.";
                throw new ContextualError(message, getLocation());
            }
        }
        setType(typeCastedToType);
        return typeCastedToType;
    }


    protected void setRightOperand(AbstractExpr rightOperand) {
        Validate.notNull(rightOperand);
        this.rightOperand = rightOperand;
    }

    private AbstractIdentifier leftOperand;
    private AbstractExpr rightOperand;

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("(");
        leftOperand.decompile(s);
        s.print(") (");
        rightOperand.decompile(s);
        s.print(")");
    }

    protected String getOperatorName() {
        return "(";
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        leftOperand.iter(f);
        rightOperand.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        leftOperand.prettyPrint(s, prefix, false);
        rightOperand.prettyPrint(s, prefix, true);
    }

    @Override
    public int codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

