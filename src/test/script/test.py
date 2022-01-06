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
counter_compatible = 0 
counter_non_compatible = 0 

tests_failed = []

for file in glob.glob("./src/test/deca/syntax/invalid/*.deca") :  
    i += 1
    if os.system(f"./src/test/script/launchers/test_synt {file} > /dev/null  2>&1" ) == 0  :
        print(f"{file}: ECHEC, echec attendu et test reussi")
        tests_failed += file
        counter_compatible +=1 
    else :
        print(f"{file}: REUSSI")
        counter_non_compatible  += 1


print(counter_compatible)
print(counter_non_compatible)








#for file in glob.glob("./src/test/deca/syntax/valid/*.deca") :  
    #txtfiles.append(file)
    #print(file)
    #print(os.getcwd())
    #os.system(f"./src/test/script/launchers/test_lex {file}")