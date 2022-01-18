package fr.ensimag.ima.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * Abstract representation of an IMA program, i.e. set of Lines.
 *
 * @author Ensimag
 * @date 01/01/2022
 */
public class IMAProgram implements OutputProgram {
    public int maxRegister;
    private final boolean shouldCheck;

    public IMAProgram(int maxRegister, boolean shouldCheck) {
        this.maxRegister = maxRegister;
        this.shouldCheck = shouldCheck;
    }

    /**
     * Should we check according to the -n (no check) options?
     * This means we should ignore 11.1 and 11.3 of [Sémantique].
     * @return true if we check.
     */
    public boolean shouldCheck() {
        return shouldCheck;
    }

    private final LinkedList<AbstractLine> lines = new LinkedList<AbstractLine>();

    public void add(AbstractLine line) {
        lines.add(line);
    }

    @Override
    public void addComment(String comment) {
        lines.add(new Line(comment));
    }

    public void addLabel(Label l) {
        lines.add(new Line(l));
    }

    public void addInstruction(Instruction i) {
        lines.add(new Line(i));
    }

    /**
     * Add an instruction with a comment.
     * @param i The instruction.
     * @param s The comment.
     */
    public void addInstruction(Instruction i, String s) {
        lines.add(new Line(null, i, s));
    }

    /**
     * Append the content of program p to the current program. The new program
     * and p may or may not share content with this program, so p should not be
     * used anymore after calling this function.
     */
    public void append(IMAProgram p) {
        lines.addAll(p.lines);
    }

    /**
     * Add a line at the front of the program.
     */
    public void addFirst(Line l) {
        lines.addFirst(l);
    }

    /**
     * Display the program in a textual form readable by IMA to stream s.
     */
    @Override
    public void display(PrintStream s) {
        for (AbstractLine l : lines) {
            l.display(s);
        }
    }

    /**
     * Return the program in a textual form readable by IMA as a String.
     */
    @Override
    public String display() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream s = new PrintStream(out);
        display(s);
        return out.toString();
    }

    public void addFirst(Instruction i) {
        addFirst(new Line(i));
    }

    public void addFirst(Instruction i, String comment) {
        addFirst(new Line(null, i, comment));
    }

    public void setMaxRegister(int maxRegister) {
        this.maxRegister = maxRegister;
    }

    private int freeRegister = 2;

    /**
     * Allocate a register. This must be freed with {@link #freeRegister()}.
     * @return The register that was allocated.
     * @throws DecacInternalError if no registers are allocable. This should never happen.
     */
    public GPRegister allocateRegister() throws DecacInternalError {
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
    public GPRegister getMaxUsedRegister() {
        return Register.getR(freeRegister);
    }

    /**
     * Can we allocate a register?
     */
    public boolean isMaxUsableRegister() {
        return freeRegister == maxRegister;
    }

    /**
     * Free a register.
     */
    public void freeRegister() {
        if (freeRegister > 2) {
            freeRegister--;
        } else {
            throw new DecacInternalError("attempted free but no registers left to free");
        }
    }

    private int varCount = 0;

    public int getVarCount() {
        addInstruction(new ADDSP(new ImmediateInteger(1)));
        return ++varCount;
    }

    private int stackUsage = 0;

    public int bumpStackUsage() {
        // TODO: figure out a way to ensure this is called at every PUSH.
        return ++stackUsage;
    }

    public int getStackUsage() {
        return stackUsage;
    }
}
