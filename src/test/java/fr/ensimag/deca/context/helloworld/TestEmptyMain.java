package fr.ensimag.deca.context.helloworld;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tree.*;
import org.junit.jupiter.api.Test;

/**
 * Test for the empty main node.
 *
 * @date 07/01/2022
 */
public class TestEmptyMain {
    @Test
    public void testEmptyMain() throws ContextualError {
        DecacCompiler compiler = new DecacCompiler(null, null);
        AbstractMain emptyMain = new EmptyMain();
        AbstractProgram program = new Program(new ListDeclClass(), emptyMain);

        program.verifyProgram(compiler);
    }
}
