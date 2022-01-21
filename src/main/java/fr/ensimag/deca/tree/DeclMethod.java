package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.IMAProgram;
import org.apache.commons.lang.Validate;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {

    final private AbstractIdentifier type;
    final private AbstractIdentifier methodName;
    final private ListDeclParam params;
    final private AbstractMethodBody methodBody;

    public DeclMethod(AbstractIdentifier type, AbstractIdentifier methodName,
                      ListDeclParam params, AbstractMethodBody methodBody) {
        Validate.notNull(type);
        Validate.notNull(methodName);
        Validate.notNull(params);
        Validate.notNull(methodBody);
        this.type = type;
        this.methodName = methodName;
        this.params = params;
        this.methodBody = methodBody;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        methodName.decompile(s);
        s.print(" ");
        params.decompile(s);
        s.print(" ");
        methodBody.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        methodName.iter(f);
        params.iter(f);
        methodBody.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        methodName.prettyPrint(s, prefix, false);
        params.prettyPrint(s, prefix, false);
        methodBody.prettyPrintChildren(s, prefix);
    }

    @Override
    protected void verifyDeclMethod(DecacCompiler compiler, EnvironmentExp localEnv,
                                    ClassDefinition currentClass, ClassDefinition superClass) throws ContextualError {
        Type methodType = type.verifyType(compiler);
        ExpDefinition superDefinition = superClass.getMembers().get(methodName.getName());
        String message = "ScopeError: la méthode `" + methodName.getName()
                + "` a une signature incompatible avec sa méthode héritée";
        MethodDefinition superMethodDefinition =
                superDefinition.asMethodDefinition(message, getLocation());
        Signature signature = superMethodDefinition.getSignature();
        boolean sameSig = signature
                .equals(params.verifyListDeclParamType(compiler));
        // TODO: finish implement subType in the context class.
        boolean sameType = Context.subType(superMethodDefinition.getType(), methodType);
        if (!sameType || !sameSig) {
            throw new ContextualError(message, getLocation());
        }
        MethodDefinition methodDefinition = new MethodDefinition(
                methodType,
                getLocation(),
                signature,
                currentClass.getNumberOfMethods()
        );
        methodName.setDefinition(methodDefinition);
        currentClass.incNumberOfMethods();
        try {
            localEnv.declare(methodName.getName(), methodDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            message = "ScopeError: tentative de définir la méthode `"
                    + methodName.decompile() + "` deux fois.";
            throw new ContextualError(message, getLocation());
        }
    }

    @Override
    protected void verifyDeclMethodBody(DecacCompiler compiler,
                                        ClassDefinition currentClass) throws ContextualError {
        Type methodType = type.verifyType(compiler);
        EnvironmentExp paramEnvironment = params.verifyListDeclParam(compiler, currentClass);
        methodBody.verifyMethodBody(compiler, paramEnvironment, currentClass, methodType);
    }

    @Override
    public int codeGen(IMAProgram program){
        return 1;
    }
}
