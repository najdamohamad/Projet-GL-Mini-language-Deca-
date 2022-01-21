package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

/**
 * Assignment, i.e. lvalue = expr.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Assign extends AbstractBinaryExpr {

    @Override
    public AbstractLValue getLeftOperand() {
        // The cast succeeds by construction, as the leftOperand has been set
        // as an AbstractLValue by the constructor.
        return (AbstractLValue) super.getLeftOperand();
    }

    public Assign(AbstractLValue leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type lvalueType = getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        AbstractExpr rvalue = getRightOperand().verifyRValue(compiler, localEnv, currentClass, lvalueType);
        Type exprType = rvalue.verifyExpr(compiler, localEnv, currentClass);
        setType(exprType);
        return exprType;
    }


    @Override
    protected String getOperatorName() {
        return "=";
    }

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        throw new DecacInternalError("unreachable");
    }

    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = getRightOperand().codeGen(program);

        // Store the return value.
        AbstractIdentifier ident = (AbstractIdentifier) getLeftOperand();
        if (ident.getVariableDefinition().isRegister()) {
            program.addInstruction(new LOAD(
                    program.getMaxUsedRegister(),
                    ident.getVariableDefinition().getRegister()
            ),
                    "return value of assignement");
        } else {
            program.addInstruction(new STORE(
                            program.getMaxUsedRegister(),
                            ident.getVariableDefinition().getAdress()
                    ),
                    "return value of assignement"
            );
        }
        return stackUsage;
    }
}
