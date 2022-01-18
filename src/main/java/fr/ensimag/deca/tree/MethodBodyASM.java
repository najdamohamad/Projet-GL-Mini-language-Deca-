package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class MethodBodyASM extends AbstractMethodBody {


    final private String text;
    final private Location location;

    public MethodBodyASM(String text, Location location) {
        Validate.notNull(text);
        Validate.notNull(location);
        this.text = text;
        this.location = location;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass){
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(text);
        s.print(" ");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    }


}