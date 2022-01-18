package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.log4j.Logger;

/**
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpCmp extends AbstractBinaryExpr {

    private static final Logger LOG = Logger.getLogger(AbstractOpArith.class);

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    public abstract Instruction getMnemonic(GPRegister reg);

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        program.addComment(getLocation() + " cmp begin");
        // Compare the results of evaluating the LHS and RHS expressions.
        program.addInstruction(new CMP(dVal, reg));
        program.addInstruction(getMnemonic(program.getMaxUsedRegister()));
        program.addComment(getLocation() + " cmp end");
    }
}
