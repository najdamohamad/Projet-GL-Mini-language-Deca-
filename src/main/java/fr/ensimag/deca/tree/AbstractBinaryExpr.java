package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractBinaryExpr extends AbstractExpr {

    private static final Logger LOG = Logger.getLogger(AbstractOpArith.class);

    public AbstractExpr getLeftOperand() {
        return leftOperand;
    }

    public AbstractExpr getRightOperand() {
        return rightOperand;
    }

    protected void setLeftOperand(AbstractExpr leftOperand) {
        Validate.notNull(leftOperand);
        this.leftOperand = leftOperand;
    }

    protected void setRightOperand(AbstractExpr rightOperand) {
        Validate.notNull(rightOperand);
        this.rightOperand = rightOperand;
    }

    private AbstractExpr leftOperand;
    private AbstractExpr rightOperand;

    public AbstractBinaryExpr(AbstractExpr leftOperand,
                              AbstractExpr rightOperand) {
        Validate.notNull(leftOperand, "left operand cannot be null");
        Validate.notNull(rightOperand, "right operand cannot be null");
        Validate.isTrue(leftOperand != rightOperand, "Sharing subtrees is forbidden");
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("(");
        getLeftOperand().decompile(s);
        s.print(" " + getOperatorName() + " ");
        getRightOperand().decompile(s);
        s.print(")");
    }

    abstract protected String getOperatorName();

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

    /**
     * Code gen for an binary op.
     * Instead of returning only the ADD, this returns the entire instruction:
     * for example ADD 3(GB), R2.
     * It also will check for overflows when dealing with floats:
     * see [Semantique] p100, Débordements lors de l’évaluation des expressions.
     */
    public abstract void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg);

    @Override
    public void codeGen(IMAProgram program) {
        LOG.trace("coding expr: " + this.decompile());
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
                LOG.trace("codeExpr: case 3: <codeExp(e, n)> avec <dval(e2)> = T et n = max");
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
                LOG.trace("codeExpr: case 4: <codeExp(e, n)> avec <dval(e2)> = T et n < max");
                // Case 4: <codeExp(e, n)> avec <dval(e2)> = T et n < max
                // No temporary needed, instead allocate one more register.

                // <codeExp(e1, n)>
                getLeftOperand().codeGen(program);
                // <codeExp(e2, n+1)>
                GPRegister regN = program.getMaxUsedRegister();
                GPRegister regNPlusOne = program.allocateRegister();
                getRightOperand().codeGen(program);
                // mnemo(op), rn+1, Rn
                program.freeRegister();
                codeGenBinaryOp(program, regNPlusOne, regN);
            }
            return;
        }

        LOG.fatal("codeExp did not have a valid match case. exp=" + this);
        throw new DecacInternalError("codeExp did not have a valid match case. exp=" + this);
    }

}
