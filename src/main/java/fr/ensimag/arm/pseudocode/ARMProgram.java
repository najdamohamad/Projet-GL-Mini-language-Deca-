package fr.ensimag.arm.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.arm.pseudocode.Register;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ARMProgram implements OutputProgram {

    /**
     * The list of sections in the IMA program.
     * Usually there will be two sections, .data and .text.
     */
    private final Map<String, List<Line>> sections;
    /**
     * Track the max used register.
     */
    private int freeRegister = 0;

    public ARMProgram() {
        sections = new HashMap<>();
        sections.put("data", new ArrayList<>());
        sections.put("text", new ArrayList<>());
    }

    public void addLineInSection(String sectionName, Line line) {
        sections.get(sectionName).add(line);
    }

    public void addFirst(Line line) {
        sections.get("text").add(0, line);
    }
    public void addInstruction(Line line) {
        sections.get("text").add(line);
    }
    /**
     * @param comment Assembly language-agnostic way of writing comments.
     */
    @Override
    public void addComment(String comment) {
        sections.get("text").add(new Comment(comment));
    }

    /**
     * Display the program in a textual form readable by IMA to stream s.
     */
    @Override
    public void display(PrintStream s) {
        for (Map.Entry<String, List<Line>> section: sections.entrySet()) {
            new Directive(section.getKey()).display(s);
            for (Line line : section.getValue()) {
                line.display(s);
            }
        }
    }

    @Override
    public String display() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream s = new PrintStream(out);
        display(s);
        return out.toString();
    }

    /**
     * Can we allocate a register?
     */
    public boolean isMaxUsableRegister() {
        /*
         * In ARM, 11 registers are free:
         * - R0 to R6
         * - R8 to R10
         * We skip R7 which is used for syscalls.
         * This leaves us with 10 registers.
         */
        int freeRegisterAmount = 10;
        return freeRegister == freeRegisterAmount - 1;
    }

    /**
     * Allocate a register. This must be freed with {@link #freeRegister()}.
     * @return The register that was allocated.
     * @throws DecacInternalError if no registers are allocable. This should never happen.
     */
    public Register allocateRegister() throws DecacInternalError {
        if (isMaxUsableRegister()) {
            throw new DecacInternalError("reached max register");
        }
        freeRegister++;
        return Register.getR(freeRegister);
    }

    /**
     * Get the max used register, ie. the last allocated one.
     * @return the register
     */
    public fr.ensimag.arm.pseudocode.Register getMaxUsedRegister() {
        return Register.getR(freeRegister);
    }

    /**
     * Free a register.
     * @throws DecacInternalError if no registers are freeable. This should never happen.
     */
    public void freeRegister() {
        freeRegister--;
        if (freeRegister > 0) {
            throw new DecacInternalError("attempted free but no registers left to free");
        }
    }

    /**
     * Increment stack usage.
     */
    public void bumpStackUsage() {
        // TODO: no-op for now, is it doable on ARM?
    }

}
