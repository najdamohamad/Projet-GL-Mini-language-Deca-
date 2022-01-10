package fr.ensimag.arm.pseudocode;

import fr.ensimag.deca.codegen.OutputProgram;
import fr.ensimag.ima.pseudocode.AbstractLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ARMProgram implements OutputProgram {

    private final Map<String, List<Line>> sections;

    public ARMProgram() {
        sections = new HashMap<>();
        sections.put("data", new ArrayList<>());
        sections.put("text", new ArrayList<>());
    }

    public void addLineInSection(String sectionName, Line line) {
        sections.get(sectionName).add(line);
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

    /**
     * @param comment Assembly language-agnostic way of writing comments.
     */
    @Override
    public void addComment(String comment) {
        sections.get("text").add(new Comment(comment));
    }

    @Override
    public String display() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream s = new PrintStream(out);
        display(s);
        return out.toString();
    }
}
