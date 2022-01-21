package fr.ensimag.ima.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.deca.tools.DecacInternalError;

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

    private boolean isAssign = true;

    public IMAProgram(int maxRegister, boolean shouldCheck) {
        this.maxRegister = maxRegister;
        this.shouldCheck = shouldCheck;
    }

    public IMAProgram(IMAProgram program) {
        this.maxRegister = program.maxRegister;
        this.shouldCheck = program.shouldCheck;
    }

    public void setAssign(boolean assign) {
        isAssign = assign;
    }

    public boolean isAssign() {
        return isAssign;
    }

    /**
     * Should we check according to the -n (no check) options?
     * This means we should ignore 11.1 and 11.3 of [Sémantique].
     *
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
     *
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

    private int freeRegister = 2; // Actually starts at 2

    public GPRegister allocateRegister() throws DecacInternalError {
        if (freeRegister == maxRegister) {
            throw new DecacInternalError("reached max register");
        }
        freeRegister++;
        GPRegister r = Register.getR(freeRegister);
        return r;
    }

    public GPRegister getMaxUsedRegister() {
        return Register.getR(freeRegister);
    }

    public boolean isMaxUsableRegister() {
        return freeRegister == maxRegister;
    }

    public void freeRegister() {
        if (freeRegister > 2) {
            freeRegister--;
        }
    }

    /**
     * The number of variables declared in the program.
     */
    private int declaredVariables = 0;

    /**
     * Tell the compiler that a new variable has been declared,
     * and return the new number of declared variables.
     * This count will be used later to calculate the stack usage with an ADDSP.
     */
    public int incrementDeclaredVariables() {
        //addInstruction(new ADDSP(new ImmediateInteger(1)));
        declaredVariables++;
        return declaredVariables;
    }

    /**
     * Get the amount of declared variables.
     */
    public int getDeclaredVariablesCount() {
        return declaredVariables;
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
