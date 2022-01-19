package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class ClassBody extends AbstractExpr {
    private ListDeclMethod listDeclMethod;
    private ListDeclField listDeclField;

    public ListDeclField getListDeclField() {
        return listDeclField;
    }

    public ListDeclMethod getListDeclMethod() {
        return listDeclMethod;
    }

    public void setListDeclField(ListDeclField listDeclField) {
        this.listDeclField = listDeclField;
    }

    public void setListDeclMethod(ListDeclMethod methods) {
        this.listDeclMethod = methods;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) {
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        listDeclField.decompile(s);
        listDeclMethod.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        listDeclMethod.iter(f);
        listDeclField.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        listDeclMethod.prettyPrint(s, prefix, false);
        listDeclField.prettyPrint(s, prefix, false);
    }


}
