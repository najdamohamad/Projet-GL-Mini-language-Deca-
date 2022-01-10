#! /bin/sh

cd "$(dirname "$0")"/../../.. || exit 1
pwd

for f in src/test/deca/syntax/invalid/test_lex/hello_world/*.deca; 
do test_lex "$f" > "${f%.deca}.lis" 2>&1 
done 

for f in src/test/deca/syntax/valid/test_lex/hello_world/*.deca; 
do test_lex "$f" > "${f%.deca}.lis" 2>&1 
done 



for f in src/test/deca/syntax/valid/test_synt/hello_world/*.deca; 
do test_synt "$f" > "${f%.deca}.lis" 2>&1 
done 


for f in src/test/deca/syntax/invalid/test_synt/hello_world/*.deca; 
do test_synt "$f" > "${f%.deca}.lis" 2>&1 
done 


echo .lis Generated successfully :)








