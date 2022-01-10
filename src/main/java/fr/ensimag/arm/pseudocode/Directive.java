package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class Directive implements Line {

    private final String name;
    private final String[] arguments;

    public Directive(String name, String... arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public void display(PrintStream s) {
        s.println("." + name + String.join(" ", arguments));
    }
}
