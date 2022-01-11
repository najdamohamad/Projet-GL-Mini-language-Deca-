package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;

public class Context {

    public static boolean subType(DecacCompiler compiler, Type type1, Type type2) {
        if (type2.isClass()) {
            if (type1.isClass()) {
                return ((ClassType) type1).isSubClassOf((ClassType) type2);
            } else if (type1.isNull()) {
                return true;
            }
        }
        return type1.sameType(type2);
    }

    public static boolean assignCompatible(DecacCompiler compiler, Type type1, Type type2) {
        if (type1.isFloat() && type2.isInt()) {
            return true;
        }
        return subType(compiler, type1, type2);
    }
}
