package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.instructions.SEQ;
import fr.ensimag.ima.pseudocode.instructions.SNE;

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
    public Instruction getMnemonic(GPRegister reg) {
        return new SNE(reg);
    }

    @Override
    public AbstractExpr invert() {
        AbstractExpr e = new Equals(getLeftOperand(), getRightOperand());
        e.setLocation(getLocation());
        e.setType(getType());
        return e;
    }
}
