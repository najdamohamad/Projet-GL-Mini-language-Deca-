package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.WNL;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Println extends AbstractPrint {

    /**
     * @param arguments arguments passed to the print(...) statement.
     * @param printHex if true, then float should be displayed as hexadecimal (printlnx)
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
    String getSuffix() {
        return "ln";
    }

    @Override
    public void decompile(IndentPrintStream s) {
        boolean hex = getPrintHex() ;
        ListExpr arg = getArguments() ;
        if(hex)
        {
            s.print("printlnx(");
        }
        else
        {
            s.print("println(");
        }
        arg.decompile(s);
        s.print(")");
    }

}
