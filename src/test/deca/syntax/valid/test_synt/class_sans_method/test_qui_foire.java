// Description:
//    Test de déclaration de class
//
// Resultats:
//    Lexeur: pas d'erreur
//    Parseur: pas d'erreur
//
// Historique:
//    cree le 11/01/2022

class coordonnees{
    int x = plan(z);
    int y = 10 + 204;

    int plan(int z){
        return z;
    }
}

{
    int k = coords.plan(1);
    coordonnees coords = new coordonnees();
    coords.x = 5;
    println(1);
    if(k instanceof int){
        println(2, 3, 4);
        }
}