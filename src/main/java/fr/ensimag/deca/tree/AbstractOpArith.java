package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;

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

    public void codeOpe(IMAProgram program, DVal value, GPRegister register) {
    }

    @Override
    public void codeGen(IMAProgram program) {

        // TODO: refactor the shared code between OpArith and OpCmp.

        // Put the result of evaluating the LHS expression into R0.
        getLeftOperand().codeGen(program);
        try {
            // Get a new register to save the result of calculating the RHS,
            // to save room for the second expression.
            // Using registers is faster than addressing the stack.
            GPRegister saveRegister = program.getNextRegister();
            program.addInstruction(new LOAD(Register.R0, saveRegister));
            getRightOperand().codeGen(program);
            program.addInstruction(new LOAD(saveRegister, Register.R1));
            program.freeRegister(); // We no longer need the saveRegister.
        } catch (DecacInternalError _) {
            program.addInstruction(new PUSH(Register.R0));
            program.bumpStackUsage();
            getRightOperand().codeGen(program);
            // Restore the saved R0 register into R1.
            program.addInstruction(new POP(Register.R1));
        }

        // Put the aithmetic operation R0 OP R1 into R0.
        switch (getOperatorName()) {
            case "+":
                program.addInstruction(new ADD(Register.R1, Register.R0));
                break;
            case "-":
                program.addInstruction(new SUB(Register.R1, Register.R0));
                break;
            case "*":
                program.addInstruction(new MUL(Register.R1, Register.R0));
                break;
            case "/":
                if (getLeftOperand().isFloat()) {
                    program.addInstruction(new CMP(new ImmediateFloat(0), Register.R0));
                    program.addInstruction(new BEQ(new Label("DivisionByZeroError")));
                    program.addInstruction(new DIV(Register.R1, Register.R0));
                } else {
                    program.addInstruction(new CMP(new ImmediateInteger(0), Register.R0));
                    program.addInstruction(new BEQ(new Label("DivisionByZeroError")));
                    program.addInstruction(new QUO(Register.R1, Register.R0));
                }
                break;
            case "%":
                program.addInstruction(new REM(Register.R1, Register.R0));
                break;
            default:
                throw new DecacInternalError("unreachable!");
        }
    }

}
