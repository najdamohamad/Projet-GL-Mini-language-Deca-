package fr.ensimag.arm.pseudocode;

import java.io.PrintStream;

/**
 * An arbitrary line of text.
 * TODO: this is a hack for Println, you can also use it whenever you're
 */
public class Text implements Operand {
    private final String value;
    public Text(String value) {
        this.value = value;
    }

    @Override
    public void display(PrintStream s) {
        s.print(value);
    }
}
