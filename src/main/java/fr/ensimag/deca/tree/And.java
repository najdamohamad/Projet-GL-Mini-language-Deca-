package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.commons.lang.Validate;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class And extends AbstractOpBool {

    public And(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "&&";
    }

    @Override
    public void codeGenControlFlow(IMAProgram program, boolean branchCondition, Label label) {
        Validate.isTrue(getLeftOperand().getType().isBoolean());
        Validate.isTrue(getRightOperand().getType().isBoolean());
        // TODO: get the correct nextFreeRegister.

        program.addComment(decompile());

        Label endLabel = new Label("cf_and_end_" + hashCode());

        // This one calculates the left expression in R2 (R0?).
        getLeftOperand().codeGen(program);
        program.addInstruction(new CMP(0, Register.getR(2)));
        if (branchCondition) {
            // If branchCondtion is true, we should jump to "end" when the result
            // of evaluating the LHS expression is 0.
            program.addInstruction(new BEQ(endLabel));
        } else {
            // If branchCondtion is false, we should jump to "label" when the result
            // of evaluating the LHS expression is 0.
            program.addInstruction(new BEQ(label));
        }
        program.addComment("No short circuit has been made at this point and LHS = 1.");

        // NOTE: we don't put the code for this before the first branch condition,
        //    1. We can reuse the same register for the two checks on LHS and RHS.
        //    2. We avoid unecessary cod execution and effectively short circuit.
        getRightOperand().codeGen(program);
        program.addInstruction(new CMP(1, Register.getR(2)));
        if (branchCondition) {
            // If branchCondtion is true, we should jump to "label" when the result
            // of evaluating the RHS expression is 1. Since AND == true.
            program.addInstruction(new BEQ(label));
        } else {
            // If branchCondtion is false, we should jump to "label" when the result
            // of evaluating the RHS expression is 0. Since AND == false.
            program.addInstruction(new BNE(label));
        }

        // We jump here when we short circuit upon evaluating the first operand.
        program.addLabel(endLabel);
    }

}
