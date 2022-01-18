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
public class ClassBody extends AbstractExpr{
        private ListDeclMethod methods;
        private ListDeclField listDeclField;

    public ListDeclField getListDeclField() {
        return listDeclField;
    }

    public ListDeclMethod getMethods() {
        return methods;
    }

    public void setListDeclField(ListDeclField listDeclField) {
        this.listDeclField = listDeclField;
    }

    public void setMethods(ListDeclMethod methods) {
        this.methods = methods;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass){
        Type intType = compiler.getType("int");
        setType(intType);
        return intType;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        listDeclField.decompile(s);
        methods.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        methods.iter(f);
        listDeclField.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        methods.prettyPrint(s, prefix, false);
        listDeclField.prettyPrint(s, prefix, false);
    }


}
