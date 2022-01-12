package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.arm.pseudocode.Immediate;
import fr.ensimag.arm.pseudocode.Label;
import fr.ensimag.arm.pseudocode.Line;
import fr.ensimag.arm.pseudocode.syscalls.Write;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.instructions.WNL;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Println extends AbstractPrint {

    /**
     * @param arguments arguments passed to the print(...) statement.
     * @param printHex  if true, then float should be displayed as hexadecimal (printlnx)
     */
    public Println(boolean printHex, ListExpr arguments) {
        super(printHex, arguments);
    }

    @Override
    public void codeGen(IMAProgram program) {
        super.codeGen(program);
        program.addInstruction(new WNL());
    }

    @Override
    public void codeGen(ARMProgram program) {
        super.codeGen(program);
        Line writeNewline = new Write(
                new Immediate(1), // fd = stdout
                new Label("newline"),
                new Immediate(1)
        );
        program.addLineInSection("text", writeNewline);
    }

    @Override
    String getSuffix() {
        return "ln";
    }

    @Override
    public void decompile(IndentPrintStream s) {
        boolean hex = getPrintHex();
        ListExpr arg = getArguments();
        if (hex) {
            s.print("printlnx(");
        } else {
            s.print("println(");
        }
        arg.decompile(s);
        s.print(");");
    }

}
