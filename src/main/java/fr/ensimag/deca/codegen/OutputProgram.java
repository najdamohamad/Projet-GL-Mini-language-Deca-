package fr.ensimag.deca.codegen;

import java.io.PrintStream;

public interface OutputProgram {

    /**
     * @param s Stream for printing the program's text representation.
     */
    void display(PrintStream s);


    /**
     * Return the program in a textual form readable as a String.
     */
    String display();

    /**
     * @param comment Assembly language-agnostic way of writing comments.
     */
    void addComment(String comment);
}
