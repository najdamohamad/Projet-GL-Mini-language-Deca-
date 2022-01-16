package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.ImmediateFloat;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.*;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.Validate;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Modulo extends AbstractOpArith {

    public Modulo(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        if (leftExprType.isInt() && rightExprType.isInt()) {
            Type intType = compiler.getType("int");
            setType(intType);
            return intType;
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression arithmétique `" + this.decompile()
                    + "`, attendu `int`.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        // Check for divide by 0
        // p.108:  OV = (V[dval] = 0)
        // in other words, OV flag if set if QUO with 0 as second operand
        program.addInstruction(new REM(dval, reg));
        program.addInstruction(new BEQ(Program.DIVISION_BY_ZERO_ERROR));
    }


    @Override
    protected String getOperatorName() {
        return "%";
    }

}
