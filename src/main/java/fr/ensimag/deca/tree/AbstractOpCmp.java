package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
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

        // TODO: refactor the shared code between OpArith and OpCmp.

        Label returnTrue = new Label("cmp_true_" + hashCode());
        Label endLabel = new Label("cmp_end_" + hashCode());

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
