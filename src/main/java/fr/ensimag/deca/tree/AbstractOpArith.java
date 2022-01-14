package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.codegen.GestionRegistre;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.REM;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.DVal;
import org.apache.commons.lang.Validate;
import fr.ensimag.ima.pseudocode.Label;

/**
 * Arithmetic binary operations (+, -, /, ...)
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {
    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        Type exprType;
        if (leftExprType.isInt() && rightExprType.isInt()) {
            exprType = compiler.getType("int");
        } else if (leftExprType.isFloat() || rightExprType.isFloat()) {
            exprType = compiler.getType("float");

            // Convert the expression which is an int with a ConvFloat node.
            if (leftExprType.isInt()) {
                setLeftOperand(new ConvFloat(getLeftOperand()));
                getLeftOperand().setType(compiler.getType("float"));
            } else if (rightExprType.isInt()) {
                setRightOperand(new ConvFloat(getRightOperand()));
                getRightOperand().setType(compiler.getType("float"));
            }
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression arithmétique `" + this.decompile()
                    + "`, attendu `float` ou bien `int`";
            throw new ContextualError(message, getLocation());
        }
        setType(exprType);
        return exprType;
    }
    public void mnemo(IMAProgram program,DVal value,GPRegister register) {
        throw new UnsupportedOperationException("not yet implemented");
    }


    public void codeGenExpr(IMAProgram program, GPRegister register){
        super.codeGen(program);
        if (this.getRightOperand().getDVal() != null){
            this.getLeftOperand().codeGenExpr(program, register);
            mnemo(program, this.getRightOperand().getDVal(), register);
        }
        else if(register.getNumber() == program.maxRegister){
            this.getLeftOperand().codeGenExpr(program, register);
            program.addInstruction(new TSTO(1));
            program.addInstruction(new BOV(new Label("stackOverflow")));
            program.addInstruction(new PUSH(register));
            this.getRightOperand().codeGenExpr(program, register);
            program.addInstruction(new LOAD(register, Register.R0));
            program.addInstruction(new POP(register));
            mnemo(program,Register.R0 ,register);
        }
        else{
            this.getLeftOperand().codeGenExpr(program, register);
            this.getRightOperand().codeGenExpr(program, Register.getR(register.getNumber()+1));
            mnemo(program,Register.getR(register.getNumber()+1),register );
        }
    }

}
