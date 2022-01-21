package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Full if/else if/else statement.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class IfThenElse extends AbstractInst {

    private final AbstractExpr condition;
    private final ListInst thenBranch;
    private ListInst elseBranch;

    public IfThenElse(AbstractExpr condition, ListInst thenBranch, ListInst elseBranch) {
        Validate.notNull(condition);
        Validate.notNull(thenBranch);
        Validate.notNull(elseBranch);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
                              ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        condition.verifyCondition(compiler, localEnv, currentClass);
        thenBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
        elseBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("if(");
        condition.decompile(s);
        s.println("){");
        s.indent();
        thenBranch.decompile(s);
        s.unindent();

        s.println("} else {");
        s.indent();
        elseBranch.decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        condition.iter(f);
        thenBranch.iter(f);
        elseBranch.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        thenBranch.prettyPrint(s, prefix, false);
        elseBranch.prettyPrint(s, prefix, true);
    }

    public ListInst getElseBranch() {
        return elseBranch;
    }

    public void setElseBranch(ListInst elseBranch) {
        this.elseBranch = elseBranch;
    }

    /**
     * Code generation for an if-then-else branch.
     * Implements the algorithm p225, 8.1. Conditionelles
     */
    @Override
    public int codeGen(IMAProgram program) {
        program.addComment(getLocation().getLine() + ": if ( "+condition.decompile()+" ) {");

        Label elseLabel = new Label("code.ifThenElse.else." + hashCode());
        Label endLabel = new Label("code.ifThenElse.end." + hashCode());

        // Optimisation: If the if clause contains a comparaison, use the related operators (BEQ, BNE, BGT...)
        // and do not codegen for the condition.
        int stackUsageCondition;
        if (condition instanceof AbstractOpCmp) {
            program.setAssign(false);
            stackUsageCondition = condition.codeGen(program);
            AbstractOpCmp cmp = ((AbstractOpCmp) ((AbstractOpCmp) condition).invert());
            program.addInstruction(cmp.getBranchMnemonic(elseLabel), "if has top-level compare, branch using mnenmonic");
            program.setAssign(true);
        } else {
            stackUsageCondition = condition.codeGen(program);
            // Go to the else clause if the returned value is false.
            program.addInstruction(new CMP(0, program.getMaxUsedRegister()), "check if clause");
            program.addInstruction(new BEQ(elseLabel));
        }

        program.addComment(getLocation().getLine() + ": then clause of if {");
        int stackUsageThen = thenBranch.codeGen(program);
        program.addInstruction(new BRA(endLabel));

        program.addComment(getLocation().getLine() + ": } else {");
        program.addLabel(elseLabel);
        int stackUsageElse = elseBranch.codeGen(program);

        program.addLabel(endLabel);

        program.addComment(getLocation().getLine() + ": } if end");
        return Math.max(stackUsageCondition, Math.max(stackUsageElse, stackUsageThen));
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
