package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * Binary expressions.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class InstanceOf extends AbstractExpr {

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
                           ClassDefinition currentClass) throws ContextualError {
        Type exprType = expression.verifyExpr(compiler, localEnv, currentClass);
        Type idType = typeInstanced.verifyType(compiler);
        String message = "TypeError: `" + expression.decompile() + "` n'est ni une classe ni null.";
        if (!exprType.isClassOrNull()) {
            throw new ContextualError(message, getLocation());
        } else if (!idType.isClass()) {
            message = "TypeError: `" + typeInstanced.decompile() + "` n'est pas une classe.";
            throw new ContextualError(message, getLocation());
        }
        Type returnType = compiler.getType("boolean");
        setType(returnType);
        return returnType;
    }

    protected void setExpression(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    private AbstractExpr expression;
    private AbstractIdentifier typeInstanced;

    public InstanceOf(AbstractExpr expression,
                      AbstractIdentifier typeInstanced) {
        Validate.notNull(expression, "Type casted to cannot be null");
        Validate.notNull(typeInstanced, "right operand cannot be null");
        Validate.isTrue(expression != typeInstanced, "Sharing subtrees is forbidden");
        this.expression = expression;
        this.typeInstanced = typeInstanced;
    }


    @Override
    public void decompile(IndentPrintStream s) {
        expression.decompile(s);
        s.print("instanceOf(");
        typeInstanced.decompile(s);
        s.print(")");
    }

    protected String getOperatorName() {
        return "(";
    }

    ;

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
        typeInstanced.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, false);
        typeInstanced.prettyPrint(s, prefix, true);
    }

    @Override
    public int codeGen(IMAProgram program) {
        Type expressionType = expression.getType();
        Type classType = typeInstanced.getType();
        while(expressionType != classType){
            if(expressionType == null){
                program.addInstruction(new LOAD(0, program.getMaxUsedRegister()));
                return 0;
            }
            else{
            }
        }
        program.addInstruction(new LOAD(1, program.getMaxUsedRegister()));
        return 0;
    }

}

