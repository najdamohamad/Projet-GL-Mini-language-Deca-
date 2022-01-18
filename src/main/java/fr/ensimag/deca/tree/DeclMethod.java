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
public class DeclMethod extends AbstractDeclMethod {


    final private AbstractIdentifier type;
    final private AbstractIdentifier name;
    final private ListDeclParam params;
    final private ListDeclVar decls;
    final private ListInst insts;

    public DeclMethod(AbstractIdentifier type, AbstractIdentifier name, ListDeclParam params, ListDeclVar decls, ListInst insts) {
        Validate.notNull(type);
        Validate.notNull(name);
        Validate.notNull(params);
        Validate.notNull(decls);
        Validate.notNull(insts);
        this.type = type;
        this.name = name;
        this.params = params;
        this.decls = decls;
        this.insts = insts;
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
        decls.decompile(s);
        s.print(" ");
        insts.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        name.iter(f);
        params.iter(f);
        decls.iter(f);
        insts.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        name.prettyPrint(s, prefix, false);
        params.prettyPrint(s, prefix, false);
        decls.prettyPrint(s, prefix, false);
        insts.prettyPrint(s, prefix, false);
    }


}
