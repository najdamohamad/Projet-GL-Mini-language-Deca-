package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

public class LabelDefinition implements Line {

    private final String name;

    public LabelDefinition(String name) {
        this.name = name;
    }

    @Override
    public void display(PrintStream s) {
        s.println(name + ":");
    }
}
