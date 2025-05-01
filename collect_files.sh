#!/usr/bin/env bash

sudo apt -y install openjdk-23-jdk
javac Main.java
java Main $1 $2 $4