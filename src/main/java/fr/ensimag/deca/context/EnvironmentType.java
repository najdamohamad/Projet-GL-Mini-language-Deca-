package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.DecacMain;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentType {
    private static Logger LOG = Logger.getLogger(EnvironmentType.class);

    private final Map<Symbol, TypeDefinition> environmentType;

    private EnvironmentType() {
        environmentType = new HashMap<>();
    }

    /**
     * Creates the env_types_predef environment (p77).
     */
    public EnvironmentType(DecacCompiler compiler) {
        this();
        Symbol voidSymbol = compiler.createSymbol("void");
        Symbol booleanSymbol = compiler.createSymbol("boolean");
        Symbol floatSymbol = compiler.createSymbol("float");
        Symbol intSymbol = compiler.createSymbol("int");
        Symbol stringSymbol = compiler.createSymbol("string");
        Symbol nullSymbol = compiler.createSymbol("null");
        environmentType.put(voidSymbol, new TypeDefinition(new VoidType(voidSymbol), null));
        environmentType.put(booleanSymbol, new TypeDefinition(new BooleanType(booleanSymbol), null));
        environmentType.put(floatSymbol, new TypeDefinition(new FloatType(floatSymbol), null));
        environmentType.put(intSymbol, new TypeDefinition(new IntType(intSymbol), null));
        environmentType.put(stringSymbol, new TypeDefinition(new StringType(stringSymbol), null));
        environmentType.put(nullSymbol, new TypeDefinition(new NullType(nullSymbol), null));
    }

    /**
     * Return the Type of the symbol in the environment, or null if the
     * symbol is undefined.
     */
    public TypeDefinition get(Symbol key) {
        return environmentType.get(key);
    }

    public static class DoubleDefException extends Exception {
        private static final long serialVersionUID = -2733379901827316442L;
    }

    /**
     * Add the definition def associated to the symbol name in the environment.
     * <p>
     * Adding a symbol which is already defined in the environment,
     * - throws DoubleDefException if the symbol is in the "current" dictionary
     * - or, hides the previous declaration otherwise.
     *
     * @param name Name of the symbol to define
     * @param def  Definition of the symbol
     * @throws DoubleDefException if the symbol is already defined at the "current" dictionary
     */
    public void declare(Symbol name, TypeDefinition def) throws DoubleDefException {
        if (environmentType.containsKey(name)) {
            throw new DoubleDefException();
        }
        environmentType.put(name, def);
    }
}
