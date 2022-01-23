package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.tree.Location;

public class ObjectType extends ClassType {

    public ObjectType(Symbol className, Location location) {
        super(className, location, null);
    }

    @Override
    public boolean isObject() {
        return true;
    }
}
