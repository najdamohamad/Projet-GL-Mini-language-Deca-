package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

/**
 * @author gl47
 * @date 01/01/2022
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type leftExprType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type rightExprType = getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        if (leftExprType.isBoolean() && rightExprType.isBoolean()) {
            Type exprType = compiler.getType("boolean");
            setType(exprType);
            return exprType;
        } else {
            String message = "TypeError: type(s) incorrect(s) dans `"
                    + "l'expression booléenne `" + this.decompile()
                    + "`, attendu `boolean`";
            throw new ContextualError(message, getLocation());
        }
    }

    /**
     * This corresponds to the function Code(C, true/false, E),
     * in section 7.2 [Codage des expressions booléennes].
     *
     * @param branchCondition If the evaluation of the current expression yields the same
     *                        value as branchCondtion, then we make a jump to label.
     * @param label           The name of the label to jump to.
     */
    public abstract void codeGenControlFlow(IMAProgram program, boolean branchCondition, Label label);

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        throw new DecacInternalError("unreachable");
    }

    @Override
    public void codeGenBinaryOp(ARMProgram program, fr.ensimag.arm.pseudocode.Operand dVal, fr.ensimag.arm.pseudocode.Register reg) {
        throw new DecacInternalError("unreachable");
    }

    @Override
    public void codeGen(IMAProgram program) {
        Label returnTrue = new Label("op_bool_true_" + hashCode());
        Label endLabel = new Label("op_bool_end" + hashCode());

        codeGenControlFlow(program, true, returnTrue);

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
    }
}
