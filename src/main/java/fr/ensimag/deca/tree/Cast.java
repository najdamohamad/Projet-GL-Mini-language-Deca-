package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.INT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
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
        Type castType = leftOperand.verifyType(compiler);
        Type exprType = rightOperand.verifyExpr(compiler, localEnv, currentClass);
        if (!Context.castCompatible(exprType, castType)) {
            String message = "TypeError: impossible de cast `"
                    + exprType + "` vers `" + castType + "`.";
            throw new ContextualError(message, getLocation());
        }
        setType(castType);
        return castType;
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
        Type castType = leftOperand.getDefinition().getType();
        Type exprType = rightOperand.getType();
        //cast vers le type de l'expression, pas de changement à faire on load juste la valeur de l'expression dans maxUsedRegister
        if (castType == exprType){
            rightOperand.codeGen(program);
        }
        if (castType.isFloat()){
            rightOperand.codeGen(program);
            program.addInstruction(new FLOAT(program.getMaxUsedRegister(), program.getMaxUsedRegister()));
        }
        if (castType.isInt()){
            rightOperand.codeGen(program);
            program.addInstruction(new INT(program.getMaxUsedRegister(), program.getMaxUsedRegister()));
        }
        //cast entre class, on verifie le (B) (a) tel que a instanceof B dans l'analyse contextuelle
        else{
            rightOperand.codeGen(program);
        }
        return 0;
    }
}

