package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BLT;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Lower extends AbstractOpIneq {

    public Lower(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<";
    }

    @Override
    public Instruction getMnemonic(Label label) {
        return new BLT(label);
    }
}
