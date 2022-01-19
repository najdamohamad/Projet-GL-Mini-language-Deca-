package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class ClassBody extends Tree {
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
