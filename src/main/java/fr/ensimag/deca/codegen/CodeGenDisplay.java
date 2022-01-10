package fr.ensimag.deca.codegen;

public interface CodeGenDisplay<Program> extends CodeGen<Program> {
    /**
     * This class extends CodeGen and is useful for Expressions as printing,
     * enforces evaluation.
     *
     * @param program Abstract representation of the IMA/ARM assembly code.
     */
    void codeGenDisplay(Program program);
}
