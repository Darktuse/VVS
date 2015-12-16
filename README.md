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

JETM
----

Para realizar pruebas no funcionales mediante JETM.
ejecutar el archivo: java -jar src\test\java\es\udc\fic\vvs\Practica1\Jetm\TestJetmServidorSimple.java
Imprimirá por terminal una tabla como la siguiente.

[INFO ] [EtmMonitor] JETM 1.2.3 started.

|                Measurement Point               |  # | Average |  Min  |   Max  |  Total  |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:agregarServidorSimple          |  9 |  11,852 | 2,511 | 18,014 | 106,665 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:altaServidorSimple             |  9 |  15,536 | 4,967 | 26,158 | 139,821 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:bajaServidorSimple             |  9 |  11,801 | 5,007 | 19,001 | 106,208 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:buscarServidorSimple           |  9 |   9,970 | 3,998 | 14,998 |  89,731 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:buscarTokenVacioTest           |  9 |  11,670 | 3,981 | 23,014 | 105,033 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:eliminarServidorSimple         |  9 |  12,598 | 8,007 | 17,991 | 113,385 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:insertarAnunciosServidorSimple |  9 |  11,572 | 4,000 | 17,051 | 104,152 |
|------------------------------------------------|----|---------|-------|--------|---------|
| BusinessService:nestedMethod                   | 64 |   8,004 | 0,009 | 14,628 | 512,232 |

[INFO ] [EtmMonitor] Shutting down JETM.

JCheck
------

Para poder ejecutar JCheck necesitamos la librería externa JCheck.jar incluida en el directorio lib del proyecto, para poder incluirlo como repositorio en maven:
```
mvn install:install-file -Dfile=lib/jcheck.jar -DgroupId=jcheck -DartifactId=jcheck -Dversion=1 -Dpackaging=jar -DgeneratedPom=true
```

Para realizar las pruebas de JCheck ejecutamos con JUnit el archivo src\test\java\es\udc\fic\vvs\Practica1\JCheck\ServidorTest.java

FindBugs
--------

Para las pruebas estáticas del proyecto.

Ejecutar con: 
- mvn test
- mvn findbugs:check

Generará un xml con los bugs y warnings que encuentre en el directorio target con la misma información que la salida por la terminal.

Cobertura
---------

Al final hemos optado por añadir el plugin de cobertura para comprobar la cobertura de ramas, no solo la de lineas. Los informes se encuentran en target/site/cobertura

Ejecutar con: mvn cobertura:cobertura
