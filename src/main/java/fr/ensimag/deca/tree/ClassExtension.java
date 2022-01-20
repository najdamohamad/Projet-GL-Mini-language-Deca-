package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;
import java.io.PrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.DVal;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.DecacInternalError;

public class ClassExtension extends AbstractIdentifier {
    private final AbstractIdentifier classExtended;

    public AbstractIdentifier getClassExtended() {
        return classExtended;
    }

    public ClassExtension(AbstractIdentifier classExtended){
        Validate.notNull(classExtended);
        this.classExtended = classExtended;
    }
    public ClassExtension(){
        this.classExtended = null;
    }
    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        ExpDefinition expDefinition = localEnv.get(getName());
        if (expDefinition == null) {
            String message = "ScopeError: l'identificateur `" + getName() + "` n'est pas défini.";
            throw new ContextualError(message, getLocation());
        }
        setDefinition(expDefinition);
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
        TypeDefinition typeDefinition = compiler.getTypeDefinition(getName().toString());
        if (typeDefinition == null) {
            String message = "ScopeError: le type `" + getName() + "` n'est pas défini.";
            throw new ContextualError(message, getLocation());
        }
        setDefinition(typeDefinition);
        return typeDefinition.getType();
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("extends ");
        classExtended.decompile(s);
    }

    @Override
    public void setDefinition(Definition definition){
        throw new UnsupportedOperationException("not yet implemented");
    }
    @Override
    public VariableDefinition getVariableDefinition(){
         return classExtended.getVariableDefinition();
    }
    @Override
    protected void checkDecoration() {
        if (getDefinition() == null) {
            throw new DecacInternalError("Identifier " + this.getName() + " has no attached Definition");
        }
    }


    @Override
    public DVal getDVal() {
        return classExtended.getVariableDefinition().getOperand();
    }
    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    public Definition getDefinition() {
        return classExtended.getDefinition();
    }

    @Override
    public ClassDefinition getClassDefinition() {
        return classExtended.getClassDefinition();
    }
    @Override
    public MethodDefinition getMethodDefinition() {
        return classExtended.getMethodDefinition();
    }

    @Override
    public FieldDefinition getFieldDefinition() {
        return classExtended.getFieldDefinition();
    }

    @Override
    public ExpDefinition getExpDefinition() {
        return classExtended.getExpDefinition();
    }

    @Override
    public Symbol getName(){
        return classExtended.getName();
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        if (classExtended != null) {
            classExtended.prettyPrintChildren(s, prefix);
        }
    }

    @Override
    String prettyPrintNode() {
        return "Null";
    }

    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void codeGen(ARMProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}