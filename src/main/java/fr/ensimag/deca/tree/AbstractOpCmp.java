package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.log4j.Logger;

/**
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpCmp extends AbstractBinaryExpr implements Invert {

    private static final Logger LOG = Logger.getLogger(AbstractOpArith.class);

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    public abstract Instruction getMnemonic(GPRegister reg);

    public abstract Instruction getBranchMnemonic(Label label);

    @Override
    public abstract AbstractExpr invert();

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        program.addComment(getLocation().getLine() + ": cmp" + decompile());
        // Compare the results of evaluating the LHS and RHS expressions.
        program.addInstruction(new CMP(dVal, reg));
        // TODO: We only need the SEQ instructions if we are doing an assignement. But how to know tbat's the case?
        if (program.isAssign()) {
            program.addInstruction(getMnemonic(program.getMaxUsedRegister()));
        }
        program.addComment(getLocation().getLine() + ": cmp end");
    }
}
