package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import java.io.PrintStream;

/**
 * @author gl47
 * @date 01/01/2022
 */
public class DeclMethod extends AbstractDeclMethod {

    private static final Logger LOG = Logger.getLogger(Program.class);

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
        int methodIndex;
        Type methodType = type.verifyType(compiler);
        Signature signature = params.verifyListDeclParamType(compiler);
        MethodDefinition superDefinition = (MethodDefinition) superClass.getMembers().get(methodName.getName());
        if (superDefinition != null) {
            // We don't increment the number of methods since this method is already
            // defined in the superclass and its index stays the same.
            methodIndex = superDefinition.getIndex();

            // Si la méthode override une autre dans la super classe alors ça
            // doit garder la même signature.
            String message = "ScopeError: la méthode `" + methodName.getName()
                    + "` a une signature incompatible avec sa méthode héritée";
            MethodDefinition superMethodDefinition =
                    superDefinition.asMethodDefinition(message, getLocation());
            Signature superSignature = superMethodDefinition.getSignature();
            boolean sameSig = superSignature.equals(signature);
            boolean sameType = Context.subType(superMethodDefinition.getType(), methodType);
            if (!sameType || !sameSig) {
                throw new ContextualError(message, getLocation());
            }
        } else {
            currentClass.incNumberOfMethods();
            methodIndex = currentClass.getTotalNumberOfMethods();
        }
        MethodDefinition methodDefinition = new MethodDefinition(
                methodType,
                getLocation(),
                signature,
                // "Le champ index est 2, car il y a déjà une méthode dans Object (equals) ; getX est donc la deuxième
                // méthode de A."
                // -- p.184, Déclaration de la méthode getX
                // Indexes always start at 1.
                // 2 + superClass.getNumberOfMethods() + currentClass.getNumberOfMethods()
                methodIndex
        );
        methodDefinition.setLabel(
                new Label("code." + currentClass.getType().getName() + "." + methodName.getName())
        );
        methodName.setDefinition(methodDefinition);
        currentClass.initLabelTable();
        currentClass.getLabelTable().put(methodDefinition.getIndex(), methodDefinition.getLabel());

        try {
            localEnv.declare(methodName.getName(), methodDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            String message = "ScopeError: tentative de définir la méthode `"
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
        LOG.debug(methodName.getName() + " = " + methodName.getMethodDefinition().getIndex()
                + ", " + methodName.getMethodDefinition().getLabel());
    }

    @Override
    public AbstractIdentifier getMethodName() {
        return methodName;
    }

    @Override
    public int codeGen(IMAProgram program) {
        int stackUsage = 0;
        MethodDefinition definition = methodName.getMethodDefinition();
        IMAProgram programMethod = new IMAProgram(program);

        stackUsage += methodBody.codeGen(programMethod);

        stackUsage += programMethod.generateMethodPrologueEpilogue(definition);
        if (stackUsage > 0) {
            // addFirst -> put operations in reverse order
            programMethod.addFirst(new BOV(Program.STACK_OVERFLOW_ERROR));
            programMethod.addFirst(new TSTO(new ImmediateInteger(stackUsage)));

        } else {
            programMethod.addComment("stack usage is 0, no TSTO added");
        }
        programMethod.addFirst(definition.getLabel());
        program.append(programMethod);
        return stackUsage;
    }

    @Override
    public int codeGenInitTable(IMAProgram program, int place) {
        program.addInstruction(new LOAD(new LabelOperand(new Label(methodName.getName().toString())), Register.R0));
        program.addInstruction(new STORE(Register.R0, new RegisterOffset(place, Register.GB)));
        return place + 1;
    }
}