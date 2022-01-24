package fr.ensimag.ima.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.ima.pseudocode.instructions.*;

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
     * Add a label at the front of the program.
     */
    public void addFirst(Label l) {
        lines.addFirst(new Line(l));
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
    private boolean firstFreeUsed = false;
    private int maxUsed = 2;

    public GPRegister allocateRegister() throws DecacInternalError {
        if (freeRegister == maxRegister) {
            throw new DecacInternalError("reached max register");
        }
        freeRegister++;
        maxUsed = Math.max(freeRegister, maxRegister);
        GPRegister r = Register.getR(freeRegister);
        return r;
    }

    public GPRegister getMaxUsedRegister() {
        firstFreeUsed = true;
        return Register.getR(freeRegister);
    }

    /**
     * Add to a program a subroutine prologue and epilogue, saving all registers used
     * in this function at the start, and unloading them at the end,
     * also outputting the return RTS.
     * For example, if the registers R2 and R3 are used in the subroutine, the outputted code will be:
     * PUSH R2
     * PUSH R3
     * body of subroutine
     * SUBSP #2
     * RTS.
     * This function should be called after filling out the method body:
     * it will prefix the prologue and epilogue.
     *
     * @return stack usage of prologue
     */
    public int generatePrologueEpilogue() {
        // XXX: this code is hard to understand
        int nbRegistersUsed;
        if (!firstFreeUsed) {
            nbRegistersUsed = 0;
        } else {
            // R2 is the max -> 1 register used.
            nbRegistersUsed = maxUsed - 1;
        }

        for (int i = 2; i < 2 + nbRegistersUsed; i++) {
            addFirst(new PUSH(Register.getR(i)));
        }
        // TODO: we may be able to use SUBSP here instead
        for (int i = 2; i < 2 + nbRegistersUsed; i++) {
            addInstruction(new POP(Register.getR(i)));
        }

        addInstruction(new RTS());
        return nbRegistersUsed;
    }

    public int generateMethodPrologueEpilogue(MethodDefinition definition) {
        addFirst(definition.getLabel());
        // XXX: this code is hard to understand
        int nbRegistersUsed;
        if (!firstFreeUsed) {
            nbRegistersUsed = 0;
        } else {
            // R2 is the max -> 1 register used.
            nbRegistersUsed = maxUsed - 1;
        }
        String labelString = definition.getLabel().toString();

        if (!definition.getType().isVoid()) {
            addLabel(new Label(labelString + ".earlyExit"));
            addInstruction(new WSTR("Erreur: sortie de la méthode " + labelString + " sans return."));
            addInstruction(new WNL());
            addInstruction(new ERROR());
        }

        addLabel(new Label(labelString.replace("code", "fin")));
        for (int i = 2; i < 2 + nbRegistersUsed; i++) {
            addFirst(new PUSH(Register.getR(i)));
        }
        // TODO: we may be able to use SUBSP here instead
        for (int i = 2; i < 2 + nbRegistersUsed; i++) {
            addInstruction(new POP(Register.getR(i)));
        }

        addInstruction(new RTS());
        return nbRegistersUsed;
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
