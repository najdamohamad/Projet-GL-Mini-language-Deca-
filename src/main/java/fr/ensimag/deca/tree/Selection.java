package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
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
        FieldDefinition definition = currentClass
                .getMembers()
                .get(attribute.getName())
                .asFieldDefinition(message, getLocation());
        ClassType classType = currentClass.getType();
        if (definition.getVisibility().equals(Visibility.PROTECTED)) {
            if (!Context.subType(exprType, classType)) {
                message = "Type: l'expression `" + expression.decompile()
                        + "` doit être un sous type de `" + classType + ".";
                throw new ContextualError(message, getLocation());
            }
            if (!Context.subType(classType, definition.getType())) {
                message = "Type: la classe `" + classType
                        + "` doit être un sous type de `" + definition.getType() + ".";
                throw new ContextualError(message, getLocation());
            }
        }
        return definition.getType();
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
        expression.prettyPrintChildren(s, prefix);
        attribute.prettyPrintChildren(s, prefix);
    }


    @Override
    public void codeGen(IMAProgram program) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}