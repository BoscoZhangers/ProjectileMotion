#!/bin/bash
echo "Running compile script for ProjectileMotion..."
cd src
javac *.java
mv *.class ..
cd ..
echo "Successfully compiled."
