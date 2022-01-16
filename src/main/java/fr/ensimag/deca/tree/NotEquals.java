package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BNE;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class NotEquals extends AbstractOpExactCmp {

    public NotEquals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }

    @Override
    public Instruction getMnemonic(Label label) {
        return new BNE(label);
    }
}
