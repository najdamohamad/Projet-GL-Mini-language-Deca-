package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
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
    public void codeGen(IMAProgram program) {
        Label returnTrue = new Label("op_bool_true_" + hashCode());
        Label endLabel = new Label("op_bool_end" + hashCode());

        codeGenControlFlow(program, true, returnTrue);

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
