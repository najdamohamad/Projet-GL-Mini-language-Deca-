package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BGE;
import fr.ensimag.ima.pseudocode.instructions.SGE;
import fr.ensimag.ima.pseudocode.instructions.SLT;

/**
 * Operator "x >= y"
 *
 * @author gl47
 * @date 01/01/2022
 */
public class GreaterOrEqual extends AbstractOpIneq {

    public GreaterOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return ">=";
    }

    @Override
    public Instruction getMnemonic(GPRegister reg) {
        return new SGE(reg);
    }

    @Override
    public Instruction getBranchMnemonic(Label label) {
        return new BGE(label);
    }

    @Override
    public AbstractExpr invert() {
        AbstractExpr e = new Lower(getLeftOperand(), getRightOperand());
        e.setLocation(getLocation());
        e.setType(getType());
        return e;
    }
}
