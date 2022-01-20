package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BLT;
import fr.ensimag.ima.pseudocode.instructions.SGE;
import fr.ensimag.ima.pseudocode.instructions.SLT;

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
    public Instruction getMnemonic(GPRegister reg) {
        return new SLT(reg);
    }

    @Override
    public Instruction getBranchMnemonic(Label label) {
        return new BLT(label);
    }

    @Override
    public AbstractExpr invert() {
        AbstractExpr e = new GreaterOrEqual(getLeftOperand(), getRightOperand());
        e.setLocation(getLocation());
        e.setType(getType());
        return e;
    }
}
