package fr.ensimag.deca.codegen;

public interface CodeGen<Program> {
    /**
     * This method inserts the relevant assembly code in the program.
     *
     * @param program Abstract representation of the IMA/ARM assembly code.
     */
    void codeGen(Program program);
}
