package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WINT;

import java.io.PrintStream;

/**
 * Integer literal
 *
 * @author gl47
 * @date 01/01/2022
 */
public class IntLiteral extends AbstractExpr {
    public int getValue() {
        return value;
    }

    @Override
    public DVal getDVal() {
        return new ImmediateInteger(value);
    }

    private int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }

    @Override
    public void codeGen(IMAProgram program) {
        program.addInstruction(new LOAD(new ImmediateInteger(value), Register.R0));
    }

    @Override
    public String prettyPrintNode() {
        return "Int (" + getValue() + ")";
    }

//    @Override
//    public void codeGenDisplay(IMAProgram program) {
//        super.codeGen(program);
//        program.addInstruction(new LOAD(new ImmediateInteger(value), Register.R1));
//        program.addInstruction(new WINT());
//    }

//    @Override
//    public void codeGenExpr(IMAProgram program, GPRegister register) {
//        super.codeGen(program);
//        program.addInstruction(new LOAD(new ImmediateInteger(value), register));
//    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Integer.toString(value));
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

}
