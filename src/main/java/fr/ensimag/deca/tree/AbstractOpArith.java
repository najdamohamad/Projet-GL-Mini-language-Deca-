package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.log4j.Logger;

/**
 * Arithmetic binary operations (+, -, /, ...)
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {
    private static final Logger LOG = Logger.getLogger(AbstractOpArith.class);
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

    /**
     * Code gen for an binary op.
     * Instead of returning only the ADD, this returns the entire instruction:
     * for example ADD 3(GB), R2.
     * It also will check for overflows when dealing with floats:
     * see [Semantique] p100, Débordements lors de l’évaluation des expressions.
     */
    public abstract void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg);

    /**
     * Implements the codeGenExp algorithm in 05-stage-gencode-trans p5.
     * Here Rn = the result of program.getMaxUsedRegister().
     */
    @Override
    public void codeGen(IMAProgram program) {
        LOG.trace("coding expr: "+this.decompile());
        // Case 1: <codeExp(e, n)> avec <dval(e)> != T
        // Overridden by each literal, see eg. IntLiteral which overrides codeGen

        // Case 2: <codeExp(op[e1, e2], n)> avec <dval(e2)> != T
        if (getRightOperand().getDVal() != null) {
            LOG.trace("codeExpr: case 2 <codeExp(op[e1, e2], n)> avec <dval(e2)> != T");
            // <codeExp(e1, n)>
            getLeftOperand().codeGen(program);
            // <mnemo(op)> <dval(e2)>, Rn
            codeGenBinaryOp(program, getRightOperand().getDVal(), program.getMaxUsedRegister());
            return;
        }

        if (getRightOperand().getDVal() == null) {
            if (program.isMaxUsableRegister()) {
                // Case 3: <codeExp(e, n)> avec <dval(e2)> = T et n = max
                // Allocate a temporary on the stack.
                program.bumpStackUsage();

                // <codeExp(e1, n)>
                getLeftOperand().codeGen(program);
                // PUSH Rn
                program.addInstruction(
                        new PUSH(program.getMaxUsedRegister())
                );
                // <codeExp(e2, n)>
                getRightOperand().codeGen(program);
                // LOAD Rn, R0
                program.addInstruction(
                        new LOAD(program.getMaxUsedRegister(), Register.R0)
                );
                // POP Rn
                program.addInstruction(
                        new POP(program.getMaxUsedRegister())
                );
                // <mnemo(op)> R0, Rn
                codeGenBinaryOp(program, Register.R0, program.getMaxUsedRegister());
            } else {
                // Case 4: <codeExp(e, n)> avec <dval(e2)> = T et n < max
                // No temporary needed, instead allocate one more register.

                // <codeExp(e1, n)>
                getLeftOperand().codeGen(program);
                // <codeExp(e2, n+1)>
                GPRegister regN = program.getMaxUsedRegister();
                GPRegister regNPlusOne = program.allocateRegister();
                codeGenBinaryOp(program, regN, regNPlusOne);
                program.freeRegister();
            }
            return;
        }

        LOG.fatal("codeExp did not have a valid match case. exp="+this);
        throw new DecacInternalError("codeExp did not have a valid match case. exp="+this);
    }
}
