package fr.ensimag.ima.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
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

    private int maxRegister = 15;

    public void setMaxRegister(int maxRegister) {
        this.maxRegister = maxRegister;
    }

    public int getMaxRegister() {
        return maxRegister;
    }

    private int freeRegister = 2;

    public GPRegister getFreeRegister() {
        return Register.getR(freeRegister);
    }

    public GPRegister bumpFreeRegister() {
        freeRegister++;
        return getFreeRegister();
    }

    public void freeRegister() {
        freeRegister--;
    }

    private int varCount = 0;

    public int getVarCount() {
        addInstruction(new ADDSP(new ImmediateInteger(1)));
        return ++varCount;
    }
}
