[![Build Status](https://travis-ci.org/Darktuse/VVS.svg)](https://travis-ci.org/Darktuse/VVS)
[![Coverage Status](https://coveralls.io/repos/Darktuse/VVS/badge.svg?branch=master&service=github)](https://coveralls.io/github/Darktuse/VVS?branch=master)
# VVS

![alt tag](https://dl.dropboxusercontent.com/u/11709639/class-diagram-with-notes-simplified.png)

Ejecución
=========

El proyecto usa maven para la ejecución de los test. Las herramientas usadas son:


Junit
-----

Para las pruebas dinámicas. 
Ejecutar con: mvn test

PIT
---

Para la calidad de las pruebas mediante mutation test.
Ejecutar con: mvn test org.pitest:pitest-maven:mutationCoverage
Generará un directorio con los informes en target/pit-reports

FindBugs
--------

Para las pruebas estáticas del proyecto.
Ejecutar con: 
- mvn test
- mvn findbugs:check
Generará un xml con los bugs y warnings que encuentre en el directorio target con la misma información que la salida por la terminal.