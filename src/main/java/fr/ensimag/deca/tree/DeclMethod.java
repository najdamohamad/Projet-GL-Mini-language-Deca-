package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;
import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.ima.pseudocode.IMAProgram;
import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {


    final private AbstractIdentifier type;
    final private AbstractIdentifier name;
    final private ListDeclParam params;
    final private AbstractMethodBody methodBody;

    public DeclMethod(AbstractIdentifier type, AbstractIdentifier name, ListDeclParam params, AbstractMethodBody methodBody) {
        Validate.notNull(type);
        Validate.notNull(name);
        Validate.notNull(params);
        Validate.notNull(methodBody);
        this.type = type;
        this.name = name;
        this.params = params;
        this.methodBody = methodBody;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass){
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        name.decompile(s);
        s.print(" ");
        params.decompile(s);
        s.print(" ");
        methodBody.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        params.iter(f);
        methodBody.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        name.prettyPrint(s, prefix, false);
        params.prettyPrint(s, prefix, false);
        methodBody.prettyPrint(s, prefix, false);
    }

    @Override
    public void codeGen(ARMProgram program){}
    @Override
    public void codeGen(IMAProgram program){}
}
