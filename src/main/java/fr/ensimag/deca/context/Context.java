package fr.ensimag.deca.context;

public class Context {

    public static boolean subType(Type type1, Type type2) {
        if (type2.isClass()) {
            if (type1.isClass()) {
                return ((ClassType) type1).isSubClassOf((ClassType) type2);
            } else if (type1.isNull()) {
                return true;
            }
        }
        return type1.sameType(type2);
    }

    public static boolean assignCompatible(Type type1, Type type2) {
        if (type1.isFloat() && type2.isInt()) {
            return true;
        }
        return subType(type1, type2);
    }

    public static boolean castCompatible(Type type1, Type type2) {
        if (type1.isVoid()) {
            return false;
        }
        return assignCompatible(type1, type2) || assignCompatible(type2, type1);
    }
}
