package fr.ensimag.deca.syntax;

import fr.ensimag.deca.CompilerOptions;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tree.AbstractProgram;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocationTest {

    public static void checkProgramLocations(File file) throws DecacInternalError {
        System.out.println("Checking locations for file: " + file);
        DecaLexer lexer = null;
        try {
            lexer = new DecaLexer(CharStreams.fromPath(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DecaParser parser = new DecaParser(tokens);
        DecacCompiler compiler = new DecacCompiler(new CompilerOptions(), null);
        lexer.setDecacCompiler(compiler);
        parser.setDecacCompiler(compiler);
        AbstractProgram program = parser.parseProgramAndManageErrors(System.err);
        if (!program.checkAllLocations()) {
            throw new DecacInternalError("Location not set in file : " + file);
        }
    }

    public static void main(String[] args) throws DecacInternalError, IOException {
        Files.walk(Paths.get("src/test/deca"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> file.getName().contains(".deca"))
                // Includes can't be resolved from the working directory of the test.
                // Too bad!
                .filter(file -> !file.getName().contains("include"))
                .filter(file -> !file.getAbsolutePath().contains("provided"))
                .filter(file -> !file.getAbsolutePath().contains("invalid"))
                .forEach(file -> checkProgramLocations(file));
    }
}
