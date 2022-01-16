package fr.ensimag.deca;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.apache.log4j.Logger;

/**
 * Main class for the command-line Deca compiler.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class DecacMain {
    private static Logger LOG = Logger.getLogger(DecacMain.class);

    public static void main(String[] args) throws InterruptedException {
        // example log4j message.
        LOG.info("Decac compiler started");
        boolean error = false;
        final CompilerOptions options = new CompilerOptions();
        try {
            options.parseArgs(args);
        } catch (CLIException e) {
            System.err.println("Error during option parsing:\n"
                    + e.getMessage());
            options.displayUsage();
            System.exit(1);
        }
        if (options.getPrintBanner()) {
            // Imprimer la banniere.
            System.out.println("Équipe 47 (JuNGLE)");
            System.exit(0);
        }
        if (options.getSourceFiles().isEmpty()) {
            options.displayUsage();
            System.exit(0);
        }
        if (options.getParallel()) {
            int nbProcs = Runtime.getRuntime().availableProcessors();
            ExecutorService executorService = Executors.newFixedThreadPool(nbProcs);

            List<Callable<Boolean>> tasks = new ArrayList<>();
            for (File source : options.getSourceFiles()) {
                tasks.add(() -> {
                    DecacCompiler compiler = new DecacCompiler(options, source);
                    return compiler.compile();
                });
            }

            List<Future<Boolean>> futures = executorService.invokeAll(tasks);

            // Wait for all futures to finish. If one fails, the whole operation
            // should fail, but compile the other files anyway.
            for (Future<Boolean> future : futures) {
                try {
                    boolean hadCompileError = future.get();
                    if (hadCompileError) {
                        LOG.debug("A parrallel compilation failed");
                        error = true;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    LOG.debug("Parrallel compilation error: "+e);
                    error = true;
                }
            }

            executorService.shutdown();
        } else {
            for (File source : options.getSourceFiles()) {
                DecacCompiler compiler = new DecacCompiler(options, source);
                if (compiler.compile()) {
                    error = true;
                }
            }
        }
        System.exit(error ? 1 : 0);
    }
}
