package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Attribut
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Selection extends AbstractLValue {
    final private AbstractExpr expression;
    final private AbstractIdentifier attribute;

    public Selection(AbstractExpr expression, AbstractIdentifier attribute) {
        Validate.notNull(expression);
        Validate.notNull(attribute);
        this.expression = expression;
        this.attribute = attribute;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        String message = "TypeError: " + expression.decompile() + "` n'est pas un objet.";
        ClassType exprType = expression
                .verifyExpr(compiler, localEnv, currentClass)
                .asClassType(message, getLocation());
        // If the field exists in the class it should be defined here.
        message = "TypeError: `" + attribute.decompile() + "` n'est pas un champ.";
        ExpDefinition definition = exprType
                .getDefinition()
                .getMembers()
                .get(attribute.getName());
        if (definition == null) {
            throw new ContextualError(message, getLocation());
        }
        FieldDefinition fieldDefinition = definition
                .asFieldDefinition(message, getLocation());
        attribute.setDefinition(fieldDefinition);
        if (fieldDefinition.getVisibility().equals(Visibility.PROTECTED)) {
            if (currentClass == null) {
                message = "ScopeError: le champ `" + attribute.decompile() + "` est protégé.";
                throw new ContextualError(message, getLocation());
            }
            ClassType classType = currentClass.getType();
            if (!Context.subType(exprType, classType)) {
                message = "Type: l'expression `" + expression.decompile()
                        + "` doit être un sous type de `" + classType + "`.";
                throw new ContextualError(message, getLocation());
            }
            ClassDefinition fieldClass = fieldDefinition.getContainingClass();
            if (!Context.subType(classType, fieldClass.getType())) {
                message = "Type: la classe `" + classType
                        + "` doit être un sous type de `" + fieldClass.getType() + "`.";
                throw new ContextualError(message, getLocation());
            }
        }
        setType(fieldDefinition.getType());
        return fieldDefinition.getType();
    }


    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print(".");
        attribute.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
        attribute.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, false);
        attribute.prettyPrint(s, prefix, false);
    }

    public DAddr getOffset(GPRegister reg) {
        return new RegisterOffset(
                attribute.getFieldDefinition().getIndex(), reg);
    }

    @Override
    public DVal getDVal() {
        return expression.getDVal();
    }

    public int codeGenNoStore(IMAProgram program) {
        program.addComment("selecting "+decompile());
        int stackUsage = expression.codeGen(program);
        if (program.shouldCheck()) {
            program.addInstruction(new CMP(new NullOperand(), program.getMaxUsedRegister()));
            program.addInstruction(new BEQ(Program.NULL_DEREF_ERROR));
        }
        return stackUsage;
    }

    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = expression.codeGen(program);
        if (program.shouldCheck()) {
            program.addInstruction(new CMP(new NullOperand(), program.getMaxUsedRegister()));
            program.addInstruction(new BEQ(Program.NULL_DEREF_ERROR));
        }
        int stackUsageAttr = attribute.codeGen(program);
        program.addComment("codegen end");
        return stackUsage;
    }
}