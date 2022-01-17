#!/bin/bash

echo '{println("Hello, world");}' > test.deca
mvn compile
./src/main/bin/decac -a test.deca
./arm-env.sh run test.s