package fr.ensimag.deca;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * User-specified options influencing the compilation.
 *
 * @author gl47
 * @date 01/01/2022
 */
public class CompilerOptions {
    public static final int QUIET = 0;
    public static final int INFO  = 1;
    public static final int DEBUG = 2;
    public static final int TRACE = 3;

    private final static String usage =
              "\t. -b    (banner)       : affiche une bannière indiquant le nom de l’équipe\n"
            + "\t. -p    (parse)        : arrête decac après l’étape de construction de    \n"
            + "\t. -v    (verification) : arrête decac après l’étape de vérifications      \n"
            + "\t                         (ne produit aucune sortie en l’absence d’erreur) \n"
            + "\t. -n    (no check)     : supprime les tests à l’exécution spécifiés dans  \n"
            + "\t                         les points 11.1 et 11.3 de la sémantique de Deca.\n"
            + "\t. -r X  (registers)    : limite les registres banalisés disponibles à     \n"
            + "\t                         R0 ... R{X-1}, avec 4 <= X <= 16                 \n"
            + "\t. -d    (debug)        : active les traces de debug. Répéter              \n"
            + "\t                       : l’option plusieurs fois pour avoir plus de       \n"
            + "\t                         traces.                                          \n"
            + "\t. -P    (parallel)     : s’il y a plusieurs fichiers sources,             \n"
            + "\t                         lance la compilation des fichiers en             \n"
            + "\t                         parallèle (pour accélérer la compilation).       \n";

    public int getDebug() {
        return debug;
    }

    public boolean getParallel() {
        return parallel;
    }

    public boolean getPrintBanner() {
        return printBanner;
    }

    public boolean getParseThenStop() {
        return parseThenStop;
    }

    public boolean getVerifyThenStop() {
        return verifyThenStop;
    }

    public List<File> getSourceFiles() {
        return Collections.unmodifiableList(sourceFiles);
    }

    private int debug = 0;
    private boolean parallel = false;
    private boolean printBanner = false;
    private boolean parseThenStop = false;
    private boolean verifyThenStop = false;
    private boolean generateARMAssembly = false;
    private List<File> sourceFiles = new ArrayList<File>();

    
    public void parseArgs(String[] args) throws CLIException {
        // A FAIRE : parcourir args pour positionner les options correctement.
        for (String token : args) {
            switch (token) {
                // Affiche une bannière indiquant le nom de l’équipe
                case "-b":
                    if (args.length != 1) {
                        System.err.println("decac: L’option ’-b’ ne peut être utilisée que sans autre option.");
                        System.exit(1);
                    }
                    printBanner = true;
                    break;
                // Arrête decac après l’étape de construction de
                // l’arbre, et affiche la décompilation de ce dernier
                case "-p":
                    if (verifyThenStop) {
                        System.err.println("decac: Les options `-p` et `-v` sont incompatibles.");
                        System.exit(1);
                    }
                    parseThenStop = true;
                    break;
                //  arrête decac après l’étape de vérifications
                case "-v":
                    if (parseThenStop) {
                        System.err.println("decac: Les options `-p` et `-v` sont incompatibles.");
                        System.exit(1);
                    }
                    verifyThenStop = true;
                    break;
                case "-a":
                    generateARMAssembly = true;
                // TODO: finish option parsing
                default:
                    sourceFiles.add(new File(token));
                    break;
            }
        }

        Logger logger = Logger.getRootLogger();
        // map command-line debug option to log4j's level.
        switch (getDebug()) {
        case QUIET: break; // keep default
        case INFO:
            logger.setLevel(Level.INFO); break;
        case DEBUG:
            logger.setLevel(Level.DEBUG); break;
        case TRACE:
            logger.setLevel(Level.TRACE); break;
        default:
            logger.setLevel(Level.ALL); break;
        }
        logger.info("Application-wide trace level set to " + logger.getLevel());

        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (assertsEnabled) {
            logger.info("Java assertions enabled");
        } else {
            logger.info("Java assertions disabled");
        }
    }

    public void displayUsage() {
        System.out.println(usage);
    }

    public boolean isGenerateARMAssembly() {
        return generateARMAssembly;
    }
}
