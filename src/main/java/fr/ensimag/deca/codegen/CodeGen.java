package fr.ensimag.deca.codegen;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.IMAProgram;

public interface CodeGen {
    /**
     * This method inserts the relevant assembly code in the program.
     *
     * @param program Abstract representation of the IMA assembly code.
     */
    int codeGen(IMAProgram program);

    /**
     * This method inserts the relevant assembly code in the program.
     *
     * @param program Abstract representation of the ARM assembly code.
     */
    void codeGen(ARMProgram program);
}
