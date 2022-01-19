package fr.ensimag.ima.pseudocode;

import java.util.Objects;

/**
 * Immediate operand representing an integer.
 * 
 * @author Ensimag
 * @date 01/01/2022
 */
public class ImmediateInteger extends DVal {
    private int value;

    public ImmediateInteger(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "#" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmediateInteger that = (ImmediateInteger) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
