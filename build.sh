#!/bin/bash

clear; time mvn clean compile assembly:single
java -jar target/CreateTables-1.0.1-jar-with-dependencies.jar
