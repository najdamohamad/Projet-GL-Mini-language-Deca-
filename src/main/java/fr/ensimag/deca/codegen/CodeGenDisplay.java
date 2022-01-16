package fr.ensimag.deca.codegen;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.IMAProgram;

public interface CodeGenDisplay extends CodeGen {
    /**
     * This class extends CodeGen and is useful for Expressions as printing,
     * enforces evaluation.
     *
     * @param program Abstract representation of the IMA assembly code.
     */
    void codeGenDisplay(IMAProgram program, boolean hexadecimal);

    /**
     * This class extends CodeGen and is useful for Expressions as printing,
     * enforces evaluation.
     *
     * @param program Abstract representation of the ARM assembly code.
     */
    void codeGenDisplay(ARMProgram program);
}
