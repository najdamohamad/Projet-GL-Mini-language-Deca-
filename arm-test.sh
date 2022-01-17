#!/bin/bash

echo '{println("Hello, world");}' > test.deca
mvn --no-transfer-progress compile
./src/main/bin/decac -a test.deca
./arm-env.sh run test.s