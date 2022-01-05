#! /bin/sh

# Auteur : gl47
# Version initiale : 01/01/2022

# Base pour un script de test de la lexicographie.
# On teste un fichier valide et un fichier invalide.
# Il est conseillé de garder ce fichier tel quel, et de créer de
# nouveaux scripts (en s'inspirant si besoin de ceux fournis).

# Il faudrait améliorer ce script pour qu'il puisse lancer test_lex
# sur un grand nombre de fichiers à la suite.

# On se place dans le répertoire du projet (quel que soit le
# répertoire d'où est lancé le script) :
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

# On stocke les résultats de l'exécution du compilateur dans un fichier
# qui garde la trace des messages du compilateur (extension .lis).
# Quand on fait les tests, comparer cette trace attendue avec la trace
# réele
temp_lis=$(mktemp /tmp/abc-script.XXXXXX)
for cas_de_test in src/test/deca/syntax/invalid/provided/*.deca
do
    echo $cas_de_test
#    if test_lex src/test/deca/syntax/invalid/provided/simple_lex.deca 2>&1 \
#        | head -n 1 | grep -q 'simple_lex.deca:[0-9]'
#    then
#        echo "ERREUR (réussite inattendue): ${cas_de_test}"
#        exit 1
#    else
#        echo "OK (échec attendu): ${cas_de_test}"
#    fi
done

# Ligne 10 codée en dur. Il faudrait stocker ça quelque part ...
if test_lex src/test/deca/syntax/invalid/provided/chaine_incomplete.deca 2>&1 \
    | grep -q -e 'chaine_incomplete.deca:10:'
then
    echo "Echec attendu pour test_lex"
else
    echo "Erreur non detectee par test_lex pour chaine_incomplete.deca"
    exit 1
fi

