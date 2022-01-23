package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * Deca Identifier
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Identifier extends AbstractIdentifier {
    private static final Logger LOG = Logger.getLogger(Identifier.class);

    @Override
    protected void checkDecoration() {
        if (getDefinition() == null) {
            throw new DecacInternalError("Identifier " + this.getName() + " has no attached Definition");
        }
    }

    @Override
    public DVal getDVal() {
        LOG.trace("dval of "+this.decompile()+" is "+getVariableDefinition().getDVal());
        return getVariableDefinition().getDVal();
    }

    @Override
    public Definition getDefinition() {
        return definition;
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * ClassDefinition.
     * <p>
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     *
     * @throws DecacInternalError if the definition is not a class definition.
     */
    @Override
    public ClassDefinition getClassDefinition() {
        try {
            return (ClassDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a class identifier, you can't call getClassDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * MethodDefinition.
     * <p>
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     *
     * @throws DecacInternalError if the definition is not a method definition.
     */
    @Override
    public MethodDefinition getMethodDefinition() {
        try {
            return (MethodDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a method identifier, you can't call getMethodDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * FieldDefinition.
     * <p>
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     *
     * @throws DecacInternalError if the definition is not a field definition.
     */
    @Override
    public FieldDefinition getFieldDefinition() {
        try {
            return (FieldDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a field identifier, you can't call getFieldDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * VariableDefinition.
     * <p>
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     *
     * @throws DecacInternalError if the definition is not a field definition.
     */
    @Override
    public VariableDefinition getVariableDefinition() {
        try {
            return (VariableDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a variable identifier, you can't call getVariableDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a ExpDefinition.
     * <p>
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     *
     * @throws DecacInternalError if the definition is not a field definition.
     */
    @Override
    public ExpDefinition getExpDefinition() {
        try {
            return (ExpDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a Exp identifier, you can't call getExpDefinition on it");
        }
    }

    @Override
    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Override
    public Symbol getName() {
        return name;
    }

    private Symbol name;

    public Identifier(Symbol name) {
        Validate.notNull(name);
        this.name = name;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        ExpDefinition expDefinition = localEnv.get(name);
        if (expDefinition == null) {
            String message = "ScopeError: l'identificateur `" + name + "` n'est pas défini.";
            throw new ContextualError(message, getLocation());
        }
        setDefinition(expDefinition);
        if (expDefinition.isMethod()) {
            String message =
                    "ScopeError: seuls les identificateurs champs, paramètres et variables peuvent être des lvalue.`";
            throw new ContextualError(message, getLocation());
        }
        Type identifierType = expDefinition.getType();
        setType(identifierType);
        return identifierType;
    }

    /**
     * Implements non-terminal "type" of [SyntaxeContextuelle] in the 3 passes
     *
     * @param compiler contains "env_types" attribute
     */
    @Override
    public Type verifyType(DecacCompiler compiler) throws ContextualError {
        // FIXME: There is no reason to pass the Symbol identifier as a String
        //        but it otherwise breaks Unit tests ...
        TypeDefinition typeDefinition = compiler.getTypeDefinition(name.getName());
        if (typeDefinition == null) {
            String message = "ScopeError: le type `" + name + "` n'est pas défini.";
            throw new ContextualError(message, getLocation());
        }
        setDefinition(typeDefinition);
        return typeDefinition.getType();
    }

    @Override
    public int codeGen(IMAProgram program) {
        if (definition.isField()) {
            FieldDefinition field = getFieldDefinition();
            field.setAdress(new RegisterOffset(field.getIndex(), program.getMaxUsedRegister()));
            LOG.trace("gen for field, dval=" + getFieldDefinition().getDVal());
                program.addInstruction(new LOAD(
                        getFieldDefinition().getDVal(),
                        program.getMaxUsedRegister()
                ), "field " + getName() + " stored in " + getFieldDefinition().getDVal());
            return 0;
        } else if (definition.isExpression()) {
            LOG.trace("gen identifier, dval=" + getVariableDefinition().getDVal());
            program.addInstruction(new LOAD(
                    getVariableDefinition().getDVal(),
                    program.getMaxUsedRegister()
            ), "identifier " + getName() + " stored in " + getVariableDefinition().getDVal());
            return 0; // no stack usage, just loading ident
        }
        throw new DecacInternalError("invalid codegen case for identifier");
    }

    public int codeGenAssignField(IMAProgram program, GPRegister reg) {
        if (definition.isField()) {
            FieldDefinition field = getFieldDefinition();
            field.setAdress(new RegisterOffset(field.getIndex(), program.getMaxUsedRegister()));
            program.addInstruction(new STORE(
                    reg,
                    getFieldDefinition().getAdress()
            ), "field " + getName() + " stored in " + getFieldDefinition().getDVal());
        }
        return 0;
    }

    private Definition definition;


    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(name.toString());
    }

    @Override
    String prettyPrintNode() {
        return "Identifier (" + getName() + ")";
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Definition d = getDefinition();
        if (d != null) {
            s.print(prefix);
            s.print("definition: ");
            s.print(d);
            s.println();
        }
    }
}
