package fr.ensimag.deca.tree;

import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.MUL;
import org.apache.log4j.Logger;


/**
 * @author gl47
 * @date 01/01/2022
 */
public class Multiply extends AbstractOpArith {
    private static final Logger LOG = Logger.getLogger(AbstractOpArith.class);

    public Multiply(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public void codeGenBinaryOp(IMAProgram program, DVal dval, GPRegister reg) {
        program.addInstruction(new MUL(dval, reg));
        LOG.trace("Multiply/codeGen: " + this.decompile()
                + " , type = " + getType() + isFloat()
                + ", OV-Checks? " + program.shouldCheck()
        );
        if (isFloat() && program.shouldCheck()) {
            LOG.trace("Multiply/codeGen: adding BOV");
            program.addInstruction(new BOV(Program.ARITHMETIC_OVERFLOW_ERROR),
                    "multiplying two floats, overflow check for MUL");
        }
    }

    @Override
    protected String getOperatorName() {
        return "*";
    }

}
