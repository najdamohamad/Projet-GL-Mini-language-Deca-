package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.Assign;
import fr.ensimag.arm.pseudocode.*;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.WSTR;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * String literal
 *
 * @author gl47
 * @date 01/01/2022
 */
public class StringLiteral extends AbstractStringLiteral {

    @Override
    public String getValue() {
        return value;
    }

    private String value;

    public StringLiteral(String value) {
        Validate.notNull(value);
        this.value = value.replace("\"", "");
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type stringType = compiler.getType("string");
        setType(stringType);
        return stringType;
    }

    @Override
    public void codeGenDisplay(IMAProgram program) {
        program.addInstruction(new WSTR(value));
    }

    @Override
    public void codeGenDisplay(ARMProgram program) {
        // Initialize the corresponding data.
        String literalName = "string_literal_" + hashCode();
        Line literalLabel = new LabelDefinition(literalName);
        program.addLineInSection("data", literalLabel);
        Line literalValue = new Directive("string", "\"" + value + "\"");
        program.addLineInSection("data", literalValue);
        Line literalLength = new Assign(
                literalName + "_len",
                new Subtraction(new Label("."), new Label(literalName))
        );
        program.addLineInSection("data", literalLength);
        // Perform the write syscall.
        Line writeString = new Write(
                new Immediate(1), // fd = stdout
                new Label(literalName),
                new Label(literalName + "_len")
        );
        program.addLineInSection("text", writeString);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("\"" + value + "\"");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    String prettyPrintNode() {
        return "StringLiteral (" + value + ")";
    }

}
