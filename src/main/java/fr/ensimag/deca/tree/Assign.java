package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.log4j.Logger;

/**
 * Assignment, i.e. lvalue = expr.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Assign extends AbstractBinaryExpr {
    private static final Logger LOG = Logger.getLogger(Assign.class);

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
        setType(rvalue.getType());
        return rvalue.getType();
    }

    @Override
    protected String getOperatorName() {
        return "=";
    }

    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = getRightOperand().codeGen(program);

        if (getLeftOperand() instanceof Identifier) {
            Identifier identifier = (Identifier) getLeftOperand();
            program.addInstruction(new STORE(program.getMaxUsedRegister(),
                    identifier.getVariableDefinition().getOperand()));
        } else { // instanceof Selection
            Selection selection = (Selection) getLeftOperand();
            GPRegister reg = program.getMaxUsedRegister();

            if (!program.isMaxUsableRegister()) {
                GPRegister selectionReg = program.allocateRegister();
                selection.codeGenNoStore(program);

                DAddr offset = selection.getOffset(program.getMaxUsedRegister());
                program.addInstruction(new STORE(reg, offset));
                program.freeRegister();
            } else {
                stackUsage += 1; // using 1 temporary
                program.addInstruction(
                        new PUSH(program.getMaxUsedRegister())
                );
                selection.codeGenNoStore(program);

                program.addInstruction(
                        new LOAD(program.getMaxUsedRegister(), Register.R0)
                );
                program.addInstruction(
                        new POP(program.getMaxUsedRegister())
                );
                DAddr offset = selection.getOffset(Register.R0);
                program.addInstruction(new STORE(program.getMaxUsedRegister(), offset));
            }
        }

        return stackUsage;
    }

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        if (dVal instanceof DAddr) {
            program.addInstruction(new STORE(reg, (DAddr) dVal));
        } else {
            program.addInstruction(new LOAD(dVal, reg));
        }
    }
}
