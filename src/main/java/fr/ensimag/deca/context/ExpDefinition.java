package fr.ensimag.deca.context;

import fr.ensimag.deca.tree.Location;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.ima.pseudocode.GPRegister;
import org.apache.commons.lang.Validate;

/**
 * Definition associated to identifier in expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public abstract class ExpDefinition extends Definition {
    // Optimisation:
    // Each variable must correspond to an adress on the stack.
    // However, this is only the observable behavior: in the actual code, we can do whatever,
    // as long as we update the stack at the end (if we're in a method call).
    // Thus some exp definitions can use a Register instead of a DAddr for speed,
    // but still have their associated DAddr.
    private GPRegister registerForVariable;
    private DAddr adress; // Must always be set, even when using a register, for updating the stack.

    public void setAdress(DAddr operand) {
        this.adress = operand;
    }
    public void setRegister(GPRegister register) {
        this.registerForVariable = register;
    }

    public boolean isRegister() {
        return registerForVariable != null;
    }

    public GPRegister getRegister() {
        Validate.notNull(registerForVariable);
        return registerForVariable;
    }

    public DAddr getAdress() {
        return adress;
    }

    public DVal getDVal() {
        if (isRegister()) {
            return registerForVariable;
        }
        return adress;
    }
    
    public ExpDefinition(Type type, Location location) {
        super(type, location);
    }

}
