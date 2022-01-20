package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.SEQ;
import fr.ensimag.ima.pseudocode.instructions.SNE;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Equals extends AbstractOpExactCmp {

    public Equals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "==";
    }

    @Override
    public Instruction getMnemonic(GPRegister reg) {
        return new SEQ(reg);
    }

    @Override
    public Instruction getBranchMnemonic(Label label) {
        return new BEQ(label);
    }

    @Override
    public AbstractExpr invert() {
        AbstractExpr e = new NotEquals(getLeftOperand(), getRightOperand());
        e.setLocation(getLocation());
        e.setType(getType());
        return e;
    }
}
