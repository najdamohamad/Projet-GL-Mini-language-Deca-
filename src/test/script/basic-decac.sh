#! /bin/sh

# Test de l'interface en ligne de commande de decac.
# Complet et comprend toutes les options.
tmpdir=.basic-decac.$$
mkdir "$tmpdir"

die () {
    echo "ERREUR: $1"
    rm -fr "$tmpdir"
    exit 1
}

PATH=./src/main/bin:"$PATH"

decac_moins_b=$(decac -b)

if [ "$?" -ne 0 ]; then
    die "decac -b a termine avec un status different de zero."
fi

if [ "$decac_moins_b" = "" ]; then
    die "decac -b n'a produit aucune sortie"
fi

if echo "$decac_moins_b" | grep -i -e "erreur" -e "error"; then
    die "La sortie de decac -b contient erreur ou error"
fi

decac_moins_d=$(decac -d -d -d)
echo "$decac_moins_d" | grep -i -e "INFO" -e "Java assertions enabled";
if [ "$?" -ne 0 ]; then
    die "Decac -d ne log pas"
fi

fichier_test="$tmpdir/test.deca"
echo "{2+2;}" > $fichier_test
decac_n=$(decac -n $fichier_test)
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas avec -n"
fi

[ -s "$tmpdir/test.ass" ] || die "Pas de fichier .ass généré"
grep "$tmpdir/test.ass" -i -e "débordement"
if [ "$?" -eq 0 ]; then
    die "Decac génère des check avec l'option -n "
fi
rm "$tmpdir/test.ass"

decac_sans_n=$(decac $fichier_test)
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas sans -n"
fi

[ -s "$tmpdir/test.ass" ] || die "Pas de fichier .ass généré"
grep "$tmpdir/test.ass" -i -e "débordement" >/dev/null
if [ "$?" -ne 0 ]; then
    die "Decac ne génère pas des check sans l'option -n "
fi

# Make sure that checks for readInt/Float are always generated, even without -n
echo "{readInt();}" > $fichier_test
decac_sans_n=$(decac -n $fichier_test)
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas un readint avec -n"
fi

grep "$tmpdir/test.ass" -i -e "BOV" >/dev/null
if [ "$?" -ne 0 ]; then
    die "Decac ne génère pas de check IO avec l'option -n, alors qu'il devrait"
fi

echo "{(((1-1)+(1-1))+((1-1)+(1-1))+((1-1)+(1-1))+((1-1)+(1-1)));}" > $fichier_test
decac_r=$(decac -r 4 $fichier_test)
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas avec -r"
fi
[ -s "$tmpdir/test.ass" ] || die "Pas de fichier .ass généré"
grep "$tmpdir/test.ass" -i -e "R4"
if [ "$?" -eq 0 ]; then
    die "Decac utilise trop de registres avec l'option -r 4"
fi

echo '{println("Hello world");}' > "$tmpdir/a.deca"
echo '{println("Goodbye world");}' > "$tmpdir/b.deca"
echo '{println(x);}' > "$tmpdir/err.deca"
decac "$tmpdir/a.deca" "$tmpdir/b.deca"
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas avec plusieurs fichiers"
fi
[ -s "$tmpdir/a.ass" ] || die "a.ass non généré"
[ -s "$tmpdir/b.ass" ] || die "b.ass non généré"

rm "$tmpdir/a.ass" "$tmpdir/b.ass"
decac -P "$tmpdir/a.deca" "$tmpdir/b.deca"
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas avec plusieurs fichiers et -P"
fi
[ -s "$tmpdir/a.ass" ] || die "a.ass non généré"
[ -s "$tmpdir/b.ass" ] || die "b.ass non généré"

rm "$tmpdir/a.ass" "$tmpdir/b.ass"
decac "$tmpdir/a.deca" "$tmpdir/err.deca" "$tmpdir/b.deca" 2>/dev/null
if [ "$?" -eq 0 ]; then
    die "Decac n'a pas retourné d'erreur pour un fichier invalide sans -P"
fi
[ -s "$tmpdir/a.ass" ] || die "a.ass non généré"
[ -s "$tmpdir/b.ass" ] || die "b.ass non généré"

rm "$tmpdir/a.ass" "$tmpdir/b.ass"
decac -P "$tmpdir/a.deca" "$tmpdir/err.deca" "$tmpdir/b.deca" 2>/dev/null
if [ "$?" -eq 0 ]; then
    die "Decac n'a pas retourné d'erreur pour un fichier invalide avec -P"
fi
[ -s "$tmpdir/a.ass" ] || die "a.ass non généré"
[ -s "$tmpdir/b.ass" ] || die "b.ass non généré"


decac -p -v 2>/dev/null
if [ "$?" -eq 0 ]; then
    die "Decac peut se lancer avec -p et -v, alors que ces options sont incompatibles"
fi

decac -b -d 2>/dev/null
if [ "$?" -eq 0 ]; then
    die "Decac peut se lancer avec -b et une autre option, alors que ces options sont incompatibles"
fi

echo "{2+2;}" > $fichier_test
decac_out=$(decac -p $fichier_test)
if [ "$?" -ne 0 ]; then
    die "Decac ne compile pas avec l'option -p"
fi
echo "$decac_out" | grep -i -e "2 + 2" >/dev/null;
if [ "$?" -ne 0 ]; then
    die "Decac ne peut pas recompiler un fichier avec l'option -p"
fi

rm -fr "$tmpdir"
echo "Pas de probleme detecté avec l'interface ligne de commande decac."
