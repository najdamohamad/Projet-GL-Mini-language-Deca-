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
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.NEW;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.WSTR;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
    public void codeGenDisplay(IMAProgram program, boolean hexadecimal) {
        program.addInstruction(new WSTR(value));
    }

    @Override
    public void codeGen(IMAProgram program) {
        program.addComment("Begin string codeGen");
        // It's important to encode the String as UTF-8 since that's
        // what IMA expects, and Java by default encodes all strings into UTF-16.
        byte[] utf8String = value.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(utf8String));
        // String utf8String = new String(bytes);
        // Allocate memory for the String on the Heap.
        program.addInstruction(
                new NEW(utf8String.length + 1, Register.R0)
        );
        // Put all the Bytes of the String on the Heap.
        for (int offset = 0; offset < utf8String.length; offset++) {
            // FIXME: (?) this makes the size of the code very large ...
            //            (one instruction per character!)

            // The bytes are negative and IMA doesn't like that (?)
            // using String.codePointAt() doesn't work either ...
            program.addInstruction(new LOAD(
                    utf8String[offset] + 256,
                    Register.R1
            ));
            program.addInstruction(new STORE(
                    Register.R1,
                    new RegisterOffset(offset, Register.R0)
            ));
        }
        // Add a zero byte to terminate it (C-Style).
        program.addInstruction(new LOAD(0, Register.R1));
        program.addInstruction(new STORE(
                Register.R1,
                new RegisterOffset(utf8String.length, Register.R0))
        );
        program.addComment("End string codeGen");
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
