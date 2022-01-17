package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tree.*;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFloatTest {

    DecacCompiler compiler;
    ReadFloat readFloat;


    @BeforeEach
    public void setup() {
        compiler = new DecacCompiler(null, null);
    }

    @Test
    public void testCodeGenReadFloat() throws ContextualError, IOException {

        ListDeclVar decls = new ListDeclVar();
        ListExpr printArguments = new ListExpr();
        printArguments.add(new ReadFloat());
        ListInst insts = new ListInst();
        insts.add(new Println(false, printArguments));
        Main main = new Main(decls, insts);

        Program program = new Program(new ListDeclClass(), main);

        IMAProgram imaProgram = new IMAProgram(15, true);
        program.verifyProgram(compiler);
        program.codeGen(imaProgram);

        String actualProgram = imaProgram.display();
        String expectedProgram = Files
                .readString(Paths.get("src/test/java/fr/ensimag/deca/codegen/readFloatTest"));
        System.out.println(actualProgram);
        System.out.println(expectedProgram);
        assert (actualProgram.equals(expectedProgram));

    }
}
