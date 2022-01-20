package fr.ensimag.deca.tree;

/**
 * An interface for boolean expressions that can be inverted.
 */
public interface Invert {
    /**
     * Invert the truth value of this boolean expression.
     * @return A new AbstractExpr that means the opposite boolean value.
     */
    public AbstractExpr invert();
}
