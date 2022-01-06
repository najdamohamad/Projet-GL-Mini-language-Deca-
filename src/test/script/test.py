#! /usr/bin/env python3
# Script pour les tests.
# Ce script va lancer tous les tests du lexeur.

import os 
import glob
from typing import Counter
#cwd = os.getcwd() 
#print(cwd)
txtfiles = []
i = 0 
counter_inattendu = 0 
counter_attendu = 0 

tests_failed = []

print( ' BEGIN TEST : "INVALID TESTS" ')
for file in glob.glob("./src/test/deca/syntax/invalid/*.deca") :  
    i += 1
    if os.system(f"./src/test/script/launchers/test_lex {file} > /dev/null  2>&1" ) == 0  :
        print(f"{file}: REUSSI, (test failed) ")
        tests_failed.append(file)
        counter_inattendu +=1 
    else :
        print(f"{file}: FAILED , (test reussi) ")
        counter_attendu  += 1

print(f"Test avec resultat attendu (failed): {counter_attendu}  ")
print(f"Test avec resultat inattendu (passed) : {counter_inattendu}  ")
if counter_inattendu > 0:
    print("Tests resultat inattendu:")
    for test in tests_failed:
        print(f"{test} ECHEC")
    



# print('BEGIN TEST : "VALID TESTS" ')
# i = 0 
# counter_inattendu = 0 
# counter_attendu = 0 

# tests_failed = []

# print( ' BEGIN TEST : "INVALID TESTS" ')
# for file in glob.glob("./src/test/deca/syntax/valid/*.deca") :  
#     i += 1
#     if os.system(f"./src/test/script/launchers/test_lex {file} > /dev/null  2>&1" ) == 0  :
#         print(f"{file}: REUSSI ")
#         counter_attendu +=1 
#     else :
#         print(f"{file}: ECHEC , reussi attendue et Echec test ")
#         tests_failed.append(file)
#         counter_inattendu  += 1

# print(f"Test avec resultat attendu : {counter_attendu}  ")
# print(f"Test avec resultat inattendu : {counter_inattendu}  ")
# if counter_inattendu > 0:
#     print("Tests resultat inattendu:")
#     for test in tests_failed:
#         print(f"{test} ECHEC")