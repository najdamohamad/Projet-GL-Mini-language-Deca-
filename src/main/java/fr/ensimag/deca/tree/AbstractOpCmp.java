package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.*;

/**
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpCmp extends AbstractBinaryExpr {

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    public void codeGen(IMAProgram program) {
        Label returnTrue = new Label("cmp_true_" + hashCode());
        Label endLabel = new Label("cmp_end_" + hashCode());

        // Put the result of evaluating the LHS expression into R0, then
        // save it into R1 to make room for the RHS expression's value.
        getLeftOperand().codeGen(program);
        // TODO: check if using R1 is appropriate.
        program.addInstruction(new LOAD(Register.R0, Register.R1));
        getRightOperand().codeGen(program);
        // Compare the results of evaluating the LHS and RHS expressions.
        program.addInstruction(new CMP(Register.R0, Register.R1));

        switch (getOperatorName()) {
            case "==":
                program.addInstruction(new BEQ(returnTrue));
                break;
            case "!=":
                program.addInstruction(new BNE(returnTrue));
                break;
            case "<":
                program.addInstruction(new BLT(returnTrue));
                break;
            case ">":
                program.addInstruction(new BGT(returnTrue));
                break;
            case ">=":
                program.addInstruction(new BGE(returnTrue));
                break;
            case "<=":
                program.addInstruction(new BLE(returnTrue));
                break;
            default:
                throw new DecacInternalError("unreachable!");
        }

        // Return false.
        program.addInstruction(new LOAD(
                new ImmediateInteger(0),
                Register.R0
        ));
        program.addInstruction(new BRA(endLabel));

        program.addLabel(returnTrue);
        // Return true.
        program.addInstruction(new LOAD(
                new ImmediateInteger(1),
                Register.R0
        ));

        program.addLabel(endLabel);
    }
}
