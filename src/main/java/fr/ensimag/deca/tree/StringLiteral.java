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
import fr.ensimag.ima.pseudocode.instructions.*;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.PrimitiveIterator;

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
        codeGenDisplay(program, false);
    }

//    @Override
//    public void codeGen(IMAProgram program) {
//        program.addComment("Begin string codeGen");
//        // Put all the Bytes of the String on the Stack.
//        for (PrimitiveIterator.OfInt it = value.codePoints().iterator(); it.hasNext(); ) {
//            int character = it.next();
//            // It's important to encode the String as UTF-8 since that's
//            // what IMA expects, and Java by default encodes String in UTF-16.
//            byte[] bytes = Character.toString(character).getBytes(StandardCharsets.UTF_8);
//            // Put the UTF-8 character into a 32 signed integer by shifting the available.
//            // e.g: The smiley emoji has the Unicode code point U+1F600 which, UTF-8 encoding is:
//            //     f0 9f 98 80, and is constructed the following way:
//            //     (0xf0 << 8 * 3) | (0x9f << 8 * 2) | (0x98 << 8) | 0x80
//            // It doesn't matter if the word ends up in unsigned representation since that's
//            // the only integer type available in IMA. Moreoever, the conversion is done under
//            // the hood for us prior to writing the character (See utf8_es.adb in IMA source code).
//            int utf8IMAWord = 0;
//            for (int i = 0; i < bytes.length; i++) {
//                int unsignedByte = Byte.toUnsignedInt(bytes[i]);
//                utf8IMAWord |= unsignedByte << 8 * (bytes.length - i - 1);
//            }
//            program.addInstruction(new LOAD(
//                    utf8IMAWord,
//                    program.getMaxUsedRegister()
//            ));
//            program.addInstruction(new STORE(
//                    program.getMaxUsedRegister(),
//                    new RegisterOffset(program.bumpStackUsage(), Register.GB)
//            ));
//        }
//        // Add a zero byte to terminate it (C-Style).
//        program.addInstruction(new LOAD(
//                0,
//                program.getMaxUsedRegister()
//        ));
//        program.addInstruction(new STORE(
//                program.getMaxUsedRegister(),
//                new RegisterOffset(program.bumpStackUsage(), Register.GB))
//        );
//        // Allocate space on the Stack.
//        program.addInstruction(new LEA(
//                new RegisterOffset(1, Register.SP),
//                program.getMaxUsedRegister()
//        ));
//        program.addInstruction(new ADDSP(value.length() + 1));
//        program.addComment("End string codeGen");
//    }

    @Override
    public void codeGenDisplay(ARMProgram program) {
        // Prepare call to printf.
        String literalName = "string_literal_" + hashCode();
        Line literalLabel = new LabelDefinition(literalName);
        program.addLineInSection("data", literalLabel);
        Line literalValue = new Directive("string", "\"" + value + "\"");
        program.addLineInSection("data", literalValue);
        Line literalLength = new Assign(
                literalName + "_len",
                new Subtraction(new Label("."), new Subtraction(new Label(literalName), new Text("1"))
        ));
        program.addLineInSection("data", literalLength);
        // Perform the write syscall.
        Line writeString = new Write(
                new ImmediateInteger(1), // fd = stdout
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
