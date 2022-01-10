#! /usr/bin/env python3
# Script pour les tests.
# Ce script va lancer tous les tests du lexeur.

import os
import sys
import glob
from pathlib import Path
import filecmp

class color:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    END = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

def replace_ending(sentence, old, new): #https://stackoverflow.com/a/61550058/13439405
    if sentence.endswith(old):
        return sentence[:-len(old)] + new
    return sentence

# Nous voudrions changer de répertoire courant, quelque soit
# le dossier dans lequel on lance le script.0
abspath = os.path.abspath(__file__)  # Répertoire absolu du lancement du script
dirname = os.path.dirname(abspath)  # Nom du répertoire
os.chdir(dirname)
# Nous sommes maintenant dans le répertoire ou le script à été lancé.
# Changer de répertoire jusqu'a atteindre la racine du projet.
os.chdir('../../../')  # changez cette ligne si vous déplacez test.py
# Après le os.chdir: nous sommes dans ./src/test
print(os.getcwd())

etapes = ["helloworld"]

etape_test_vers_programme = {
    'lexeur': './src/test/script/launchers/test_lex',
    'syntaxe': './src/test/script/launchers/test_synt',
    'contexte': './src/test/script/launchers/test_context',
    # Pour la génération de code, on peut à la place lancer le decac directement.
    'codegen': './src/main/bin/decac',
}

nb_tests_total = 0
nb_echecs_total = 0
tous_test_echoues = []


def suite_test(dossier, sous_language, type_test, etape_test):
    """
    Lance une suite de tests invalide sur une etape specifique.
    :param dossier Le répertoire ou se trouve les tests.
    :param sous_language Le sous language qu'on veut lancer (helloworld, objet...)
    :param etape_test L'etape qu'on test (lexeur, parseur, context, codegen)
    :param type_test Le type de test (invalid, valid, perf...)
    :return:
    """
    # global: je veux modifier les variable globales
    global nb_tests_total, nb_echecs_total, tous_test_echoues

    if sous_language not in etapes:
        print(f'{color.FAIL}ERREUR{color.END}: l\'etape "{sous_language}" n\'existe pas. Etapes disponibles: {etapes}')
        sys.exit(1)

    if not Path(dossier).exists(): # https://stackoverflow.com/a/44228213/13439405
        print(f"{color.FAIL}ERREUR{color.END}: le dossier {dossier} n'existe pas.")
        sys.exit(1)

    print(f'{color.HEADER}[SUITE]{color.END} Tests {etape_test}, {sous_language} ({color.BOLD}invalid{color.END}):')

    programme = etape_test_vers_programme[etape_test]

    i = 0
    nb_echecs = 0
    tests_echoues = []

    tests = glob.glob(f"{dossier}/{sous_language}/{type_test}/*.deca")
    nb_tests = len(tests)

    for fichier in tests:
        i += 1
        nb_tests_total += 1
        print(f"[{i}/{nb_tests}] ", end='')
        fichier_nom_court = fichier.removeprefix("src/test/deca/")


        if type_test == 'invalid':
            # On stocke la sortie dans un temp.lis, ce fichier sera celui à comparer.
            if os.system(f"{programme} {fichier} > temp.lis 2>&1") == 0:
                print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court}, test réussi alors qu'il devait échouer")
                tests_echoues.append(fichier_nom_court)
                tous_test_echoues.append(fichier_nom_court)
                nb_echecs += 1
                nb_echecs_total += 1
            else:
                # comparer que la sortie est bien celle a laquelle on s'attend
                # pour tout test en fichier .deca, il y a un .lis correspondant qui donne la trace
                fichier_lis = replace_ending(fichier, '.deca', '.lis')
                if not Path(fichier_lis).exists():
                    print(f"{color.WARNING}AVERTISSEMENT{color.END}: pas de trace .lis trouvée pour {fichier_nom_court}")
                else:
                    if filecmp.cmp(fichier_lis, 'temp.lis'):
                        print(f"{color.OKGREEN}REUSSI{color.END}: {fichier_nom_court}")
                    else:
                        print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court} diffère du .lis")
                        tests_echoues.append(fichier_nom_court)
                        tous_test_echoues.append(fichier_nom_court)
                        nb_echecs += 1
                        nb_echecs_total += 1
        else: # type_test: valid, perf...
            if os.system(f"{programme} {fichier} > temp.lis 2>&1") == 0:
                # comparer que la sortie est bien celle a laquelle on s'attend
                # pour tout test en fichier .deca, il y a un .lis correspondant qui donne la trace
                fichier_lis = replace_ending(fichier, '.deca', '.lis')
                if not Path(fichier_lis).exists():
                    print(f"{color.WARNING}AVERTISSEMENT{color.END}: pas de trace .lis trouvée pour {fichier_nom_court}")
                else:
                    if filecmp.cmp(fichier_lis, 'temp.lis'):
                        print(f"{color.OKGREEN}REUSSI{color.END}: {fichier_nom_court}")
                    else:
                        print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court} diffère du .lis")
                        tests_echoues.append(fichier_nom_court)
                        tous_test_echoues.append(fichier_nom_court)
                        nb_echecs += 1
                        nb_echecs_total += 1
            else:
                print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court}, test échoué alors qu'il devait reussir")
                tests_echoues.append(fichier_nom_court)
                tous_test_echoues.append(fichier_nom_court)
                nb_echecs += 1
                nb_echecs_total += 1

    print(f'{color.HEADER}[SUITE]{color.END} {color.BOLD}RESULTAT{color.END} '
          f'Tests {etape_test}, {sous_language} ({color.BOLD}invalid{color.END}): Tests lancés: {nb_tests}, Echecs: {nb_echecs}');
    if nb_echecs > 0:
        print(f"{color.FAIL}[ECHECS]{color.END} Liste des tests échoués:")
        for test in tests_echoues:
            print(f"{color.FAIL}ECHEC{color.END} {test}")

def suite_test_lex(dossier, sous_language, type_test):
    suite_test(dossier, sous_language, type_test, 'lexeur')
def suite_test_synt(dossier, sous_language, type_test):
    suite_test(dossier, sous_language, type_test, 'syntaxe')
def suite_test_context(dossier, sous_language, type_test):
    suite_test(dossier, sous_language, type_test, 'contexte')

def suite_test_execution(dossier, sous_language, type_test):
    # global: je veux modifier les variable globales
    global nb_tests_total, nb_echecs_total, tous_test_echoues

    if sous_language not in etapes:
        print(f'{color.FAIL}ERREUR{color.END}: l\'etape "{sous_language}" n\'existe pas. Etapes disponibles: {etapes}')
        sys.exit(1)

    if not Path(dossier).exists(): # https://stackoverflow.com/a/44228213/13439405
        print(f"{color.FAIL}ERREUR{color.END}: le dossier {dossier} n'existe pas.")
        sys.exit(1)

    print(f'{color.HEADER}[SUITE]{color.END} Tests execution, {sous_language} ({color.BOLD}invalid{color.END}):')

    i = 0
    nb_echecs = 0
    tests_echoues = []

    tests = glob.glob(f"{dossier}/{sous_language}/{type_test}/*.deca")
    nb_tests = len(tests)

    for fichier in tests:
        i += 1
        nb_tests_total += 1
        print(f"[{i}/{nb_tests}] ", end='')
        fichier_nom_court = fichier.removeprefix("src/test/deca/")

        # Compiler le programme avec decac. Cela doit toujours marcher, on tente lexecution apres
        if os.system(f"./src/main/bin/decac {fichier}") != 0:
            print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court}, la compilation de {fichier_nom_court} a echoue")

        if type_test == 'invalid':
            # On stocke la sortie dans un temp.lis, ce fichier sera celui à comparer.
            if os.system(f"{programme} {fichier} > temp.lis 2>&1") == 0:
                print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court}, test réussi alors qu'il devait échouer")
                tests_echoues.append(fichier_nom_court)
                tous_test_echoues.append(fichier_nom_court)
                nb_echecs += 1
                nb_echecs_total += 1
            else:
                # comparer que la sortie est bien celle a laquelle on s'attend
                # pour tout test en fichier .deca, il y a un .lis correspondant qui donne la trace
                fichier_lis = replace_ending(fichier, '.deca', '.lis')
                if not Path(fichier_lis).exists():
                    print(f"{color.WARNING}AVERTISSEMENT{color.END}: pas de trace .lis trouvée pour {fichier_nom_court}")
                else:
                    if filecmp.cmp(fichier_lis, 'temp.lis'):
                        print(f"{color.OKGREEN}REUSSI{color.END}: {fichier_nom_court}")
                    else:
                        print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court} diffère du .lis")
                        tests_echoues.append(fichier_nom_court)
                        tous_test_echoues.append(fichier_nom_court)
                        nb_echecs += 1
                        nb_echecs_total += 1
        else: # type_test: valid, perf...
            if os.system(f"{programme} {fichier} > temp.lis 2>&1") == 0:
                # comparer que la sortie est bien celle a laquelle on s'attend
                # pour tout test en fichier .deca, il y a un .lis correspondant qui donne la trace
                fichier_lis = replace_ending(fichier, '.deca', '.lis')
                if not Path(fichier_lis).exists():
                    print(f"{color.WARNING}AVERTISSEMENT{color.END}: pas de trace .lis trouvée pour {fichier_nom_court}")
                else:
                    if filecmp.cmp(fichier_lis, 'temp.lis'):
                        print(f"{color.OKGREEN}REUSSI{color.END}: {fichier_nom_court}")
                    else:
                        print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court} diffère du .lis")
                        tests_echoues.append(fichier_nom_court)
                        tous_test_echoues.append(fichier_nom_court)
                        nb_echecs += 1
                        nb_echecs_total += 1
            else:
                print(f"{color.FAIL}ECHEC{color.END}: {fichier_nom_court}, test échoué alors qu'il devait reussir")
                tests_echoues.append(fichier_nom_court)
                tous_test_echoues.append(fichier_nom_court)
                nb_echecs += 1
                nb_echecs_total += 1

    print(f'{color.HEADER}[SUITE]{color.END} {color.BOLD}RESULTAT{color.END} '
          f'Tests {etape_test}, {sous_language} ({color.BOLD}invalid{color.END}): Tests lancés: {nb_tests}, Echecs: {nb_echecs}');
    if nb_echecs > 0:
        print(f"{color.FAIL}[ECHECS]{color.END} Liste des tests échoués:")
        for test in tests_echoues:
            print(f"{color.FAIL}ECHEC{color.END} {test}")

# WHich tests to run
# a modifier si on veut ajouter de nouveau test ajouter un nouvelle ligne suite_test()
suite_test_lex('src/test/deca/syntax/test_lex', 'helloworld', 'invalid')
# suite_test('src/test/deca/syntax/test_lex', 'helloworld', 'valid', 'lexeur')
# suite_test('src/test/deca/syntax/test_synt', 'helloworld', 'invalid', 'syntaxe')
# suite_test('src/test/deca/syntax/test_synt', 'helloworld', 'valid', 'syntaxe')
# suite_test('src/test/deca/codegen', 'helloworld', 'valid', 'syntaxe')
print()
print(f'{color.HEADER}{color.BOLD}[RAPPORT GLOBAL]{color.END}: Tests lancés: {nb_tests_total}, Echec: {nb_echecs_total}')
print(f'Liste des tests échoués:')
for test in tous_test_echoues:
    print(f"{color.FAIL}ECHEC{color.END} {test}")

if nb_echecs_total > 0:
    sys.exit(1) # echec de la suite de test
else:
    sys.exit(0)