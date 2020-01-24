#!/bin/sh
#
# script to run java app Loadtest
#
DEV_HOME=/Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-loadtest/Loadtest
#
cd $DEV_HOME
#
MYCP=$DEV_HOME/classes:$DEV_HOME/Jars/log4j-1.2.13.jar
#
LOADTEST_FILE=$1
#
java -cp $MYCP io.johnvincent.loadtest.AppGui $LOADTEST_FILE
