package fr.ensimag.deca.tree;

import fr.ensimag.arm.pseudocode.ARMProgram;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.CodeGen;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class Main extends AbstractMain implements CodeGen {
    private static final Logger LOG = Logger.getLogger(Main.class);

    private ListDeclVar declVariables;
    private ListInst insts;

    public Main(ListDeclVar declVariables,
                ListInst insts) {
        Validate.notNull(declVariables);
        Validate.notNull(insts);
        this.declVariables = declVariables;
        this.insts = insts;
    }

    @Override
    protected void verifyMain(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify Main: start");
        // A FAIRE: Appeler méthodes "verify*" de ListDeclVarSet et ListInst.
        // Vous avez le droit de changer le profil fourni pour ces méthodes
        // (mais ce n'est à priori pas nécessaire).
        EnvironmentExp mainEnvironment = new EnvironmentExp(null);
        declVariables.verifyListDeclVariable(compiler, mainEnvironment, null);
        insts.verifyListInst(
                compiler,
                mainEnvironment,
                null,
                compiler.getType("void")
        );
        LOG.debug("verify Main: end");
    }

    @Override
    public void codeGen(IMAProgram program) {
        // A FAIRE: traiter les déclarations de variables.
        program.addComment("Beginning of main function:");
        insts.codeGen(program);
        program.addComment("End of main function.");
    }

    @Override
    public void codeGen(ARMProgram program) {
        // A FAIRE: traiter les déclarations de variables.
        program.addComment("Beginning of main function:");
        insts.codeGen(program);
        program.addComment("End of main function.");
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.println("{");
        s.indent();
        LOG.debug("decompile declVar");
        declVariables.decompile(s);
        insts.decompile(s);
        s.unindent();
        s.println("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        declVariables.iter(f);
        insts.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        declVariables.prettyPrint(s, prefix, false);
        insts.prettyPrint(s, prefix, true);
    }
}
