package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import org.apache.commons.lang.NotImplementedException;
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

    public abstract Instruction getMnemonic(Label label);

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        program.addComment(getLocation() + " cmp begin");
        // Compare the results of evaluating the LHS and RHS expressions.

        Label returnTrue = new Label("cmp_true_" + hashCode());
        Label endLabel = new Label("cmp_end_" + hashCode());

        LOG.debug("dval = " + dVal);
        LOG.debug("reg = " + reg);
        program.addInstruction(new CMP(dVal, reg));
        program.addInstruction(getMnemonic(returnTrue));
        // Return false.
        program.addInstruction(new LOAD(
                new ImmediateInteger(0),
                program.getMaxUsedRegister()
        ));
        program.addInstruction(new BRA(endLabel));

        program.addLabel(returnTrue);
        // Return true.
        program.addInstruction(new LOAD(
                new ImmediateInteger(1),
                program.getMaxUsedRegister()
        ));

        program.addLabel(endLabel);
        program.addComment(getLocation() + " cmp end");
    }

    @Override
    public void codeGenBinaryOp(ARMProgram program, fr.ensimag.arm.pseudocode.Operand dVal, fr.ensimag.arm.pseudocode.Register reg) {
        throw new NotImplementedException("todo");
    }
}
