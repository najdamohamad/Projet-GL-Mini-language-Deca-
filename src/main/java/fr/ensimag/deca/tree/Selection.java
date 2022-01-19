package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.arm.pseudocode.Assign;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WINT;
import org.apache.commons.lang.Validate;
import fr.ensimag.ima.pseudocode.DVal;
import java.io.PrintStream;
import fr.ensimag.arm.pseudocode.ARMProgram;
/**
 * Attribut
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Selection extends AbstractLValue {
    final private AbstractExpr expression;
    final private AbstractIdentifier attribut;

    public AbstractExpr getExpression() {
        return expression;
    }

    public AbstractIdentifier getAttribut() {
        return attribut;
    }

    public Selection(AbstractExpr expression, AbstractIdentifier attribut) {
        Validate.notNull(expression);
        Validate.notNull(attribut);
        this.expression = expression;
        this.attribut = attribut;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }



    public void codeGenExpr(IMAProgram program,GPRegister register) {
    }

    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print(".");
        attribut.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
        attribut.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrintChildren(s, prefix);
        attribut.prettyPrintChildren(s, prefix);
    }

    @Override
    public void codeGen(ARMProgram program){}
    @Override
    public void codeGen(IMAProgram program){}
}