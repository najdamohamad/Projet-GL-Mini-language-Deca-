package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class While extends AbstractInst {
    private AbstractExpr condition;
    private ListInst body;

    public AbstractExpr getCondition() {
        return condition;
    }

    public ListInst getBody() {
        return body;
    }

    public While(AbstractExpr condition, ListInst body) {
        Validate.notNull(condition);
        Validate.notNull(body);
        this.condition = condition;
        this.body = body;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
                              ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        condition.verifyCondition(compiler, localEnv, currentClass);
        body.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("while(");
        getCondition().decompile(s);
        s.println("){");
        s.indent();
        getBody().decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        condition.iter(f);
        body.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        body.prettyPrint(s, prefix, true);
    }

    @Override
    public int codeGen(IMAProgram program) {
        Label condition = new Label("code.while.condition." + hashCode());
        Label endLabel = new Label("code.while.end." + hashCode());

        program.addComment(getLocation().getLine() + ": while ("+getCondition().decompile()+") {");
        program.addLabel(condition);

        int stackUsageCondition = getCondition().codeGen(program);
        // Optimisation: We can elide CMP #0, Rn here. TODO: verify this
        // The condition will have generated some code, the last of which will be some sort of LOAD.
        // A side effect of LOAD is that it sets the CC flags like a CMP #0 was done (p.107), so no need to
        // CMP explicitely.
        //program.addInstruction(new CMP(new ImmediateInteger(0), program.getMaxUsedRegister()));
        program.addInstruction(new BEQ(endLabel));
        int stackUsageBody = getBody().codeGen(program);
        program.addInstruction(new BRA(condition));
        program.addLabel(endLabel);
        program.addComment(getLocation().getLine() + ": } while end");

        return Math.max(stackUsageCondition, stackUsageBody);
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}
