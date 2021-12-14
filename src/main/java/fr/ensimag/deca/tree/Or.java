package fr.ensimag.deca.tree;


/**
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Or extends AbstractOpBool {

    public Or(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "||";
    }


}
