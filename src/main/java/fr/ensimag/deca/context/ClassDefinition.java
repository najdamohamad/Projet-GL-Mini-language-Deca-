package fr.ensimag.deca.context;

import fr.ensimag.deca.tree.Location;
import fr.ensimag.ima.pseudocode.Label;
import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * Definition of a class.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class ClassDefinition extends TypeDefinition {

    public void setMethodTableAddr(int methodTableAddr) {
        this.methodTableAddr = methodTableAddr;
    }


    public int getMethodTableAddr() {
        if (getType().isObject()) {
            // The Method Table starts at 1(GB) with the Object class.
            return 1;
        }
        return methodTableAddr;
    }

    private Map<Integer, Label> labelTable = new TreeMap<>();

    public void initLabelTable() {
        labelTable.put(1, new Label("code.Object.equals"));

        // Deep copy
        Map<Integer, Label> clone = new HashMap<>();
        for(Map.Entry<Integer, Label> entry : labelTable.entrySet()) {
            clone.put(entry.getKey(), new Label(entry.getValue()));
        }

        labelTable.putAll(superClass.getLabelTable());
    }

    public Map<Integer, Label> getLabelTable() {
        return labelTable;
    }

    /**
     * The address (on the Stack) of the class' table of methods.
     * <p>
     * It is stored in the compiler's memory so that we know where the method
     * of each Class lives (roughly its label); for calls of the form obj.method():
     * <li>We query obj for its type, which should be a ClassType.</li>
     * <li>The ClassType contains a ClassDefinition (this).</li>
     * <li>This methodTableAddr field contains the label of Obj.method.</li>
     * <li>Using that label we can jump to Obj.method's code and run it.</li>
     * <p>
     * Hence this field should be set upon construction of the table of methods;
     * in the first pass of code generation.
     * <p>
     * For example, it equals 1(GB) for the Object class.
     */
    private int methodTableAddr;

    public void setNumberOfFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    /**
     * Get the number of fields of this method, including inherited fields.
     *
     * @return The number of fields
     */
    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void incNumberOfFields() {
        this.numberOfFields++;
    }

    /**
     * Get the size of this object, defined as the number of fields plus one
     * for the pointer to the vtable.
     *
     * @return The size of this object.
     */
    public int getObjectSize() {
        return getNumberOfFields() + 1;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(int n) {
        Validate.isTrue(n >= 0);
        numberOfMethods = n;
    }


    public int incNumberOfMethods() {
        numberOfMethods++;
        return numberOfMethods;
    }

    private int numberOfFields = 0;
    private int numberOfMethods = 0;

    public int getTotalNumberOfMethods() {
        if (getType().isObject()) {
            // Corresponds to the Object.equals method.
            return 1;
        }
        return getNumberOfMethods() + superClass.getTotalNumberOfMethods();
    }

    @Override
    public boolean isClass() {
        return true;
    }


    @Override
    public ClassType getType() {
        // Cast succeeds by construction because the type has been correctly set
        // in the constructor.
        return (ClassType) super.getType();
    }

    ;

    public ClassDefinition getSuperClass() {
        return superClass;
    }

    private final EnvironmentExp members;
    private final ClassDefinition superClass;
    public List<String> listMethod;

    public EnvironmentExp getMembers() {
        return members;
    }

    public ClassDefinition(ClassType type, Location location, ClassDefinition superClass) {
        super(type, location);
        EnvironmentExp parent;
        if (superClass != null) {
            parent = superClass.getMembers();
        } else {
            parent = null;
        }
        members = new EnvironmentExp(parent);
        this.superClass = superClass;
        this.listMethod = new ArrayList<String>();
    }


}
