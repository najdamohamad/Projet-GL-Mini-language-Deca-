package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.STORE;
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
        // Optimisation: if variable in a register, code it directly into the register
        LOG.trace("assign, left operand:" + getLeftOperand().decompile());
        if (getLeftOperand() instanceof Selection) {
            LOG.trace("assign, left op instanceof Selection");
            program.addComment("right op codegen");
            int stackUsageRight = getRightOperand().codeGen(program);
            program.addComment("left op codegen");
            Selection s = (Selection) getLeftOperand();

            int stackUsageAssign;
            if (program.isMaxUsableRegister()) {
                program.addInstruction(new PUSH(program.getMaxUsedRegister()));
                stackUsageAssign = s.codeGenAssign(program, program.getMaxUsedRegister());
                program.addInstruction(new LOAD(program.getMaxUsedRegister(), Register.R0));
                // POP Rn
                program.addInstruction(
                        new POP(program.getMaxUsedRegister())
                );
            } else {
                GPRegister regN = program.getMaxUsedRegister();
                program.allocateRegister();
                stackUsageAssign = s.codeGenAssign(program, regN);
                program.freeRegister();
            }

            return Math.min(stackUsageAssign, stackUsageRight);
        } else {
            int stackUsageRight = getRightOperand().codeGen(program);
            AbstractIdentifier ident = (AbstractIdentifier) getLeftOperand();
            if (ident.getDefinition().isExpression()) {
                if (ident.getDefinition().isExpression()) {
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
                } else if (ident.getDefinition().isField()) {
                    if (ident.getFieldDefinition().isRegister()) {
                        program.addInstruction(new LOAD(
                                        program.getMaxUsedRegister(),
                                        ident.getFieldDefinition().getRegister()
                                ),
                                "return value of assignement");
                    } else {
                        program.addInstruction(new STORE(
                                        program.getMaxUsedRegister(),
                                        ident.getFieldDefinition().getAdress()
                                ),
                                "return value of assignement"
                        );
                    }
                } else {
                    throw new DecacInternalError("match failed for assign codegen");
                }
            }
            return stackUsageRight;
        }
    }

    public void codeGenBinaryOp(IMAProgram program, DVal dVal, GPRegister reg) {
        if (dVal instanceof DAddr) {
            program.addInstruction(new STORE(reg, (DAddr) dVal));
        } else {
            program.addInstruction(new LOAD(dVal, reg));
        }
    }
}
