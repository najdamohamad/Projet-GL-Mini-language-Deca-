package fr.ensimag.deca.tree;


import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BLE;
import fr.ensimag.ima.pseudocode.instructions.SGT;
import fr.ensimag.ima.pseudocode.instructions.SLE;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class LowerOrEqual extends AbstractOpIneq {
    public LowerOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<=";
    }

    @Override
    public Instruction getMnemonic(GPRegister reg) {
        return new SLE(reg);
    }

    @Override
    public Instruction getBranchMnemonic(Label label) {
        return new BLE(label);
    }

    @Override
    public AbstractExpr invert() {
        AbstractExpr e = new Greater(getLeftOperand(), getRightOperand());
        e.setLocation(getLocation());
        e.setType(getType());
        return e;
    }
}
