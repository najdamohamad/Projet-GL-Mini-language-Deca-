package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Comment implements Line {
    private final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void display(PrintStream s) {
        s.println("/* " + comment + " */");
    }
}
