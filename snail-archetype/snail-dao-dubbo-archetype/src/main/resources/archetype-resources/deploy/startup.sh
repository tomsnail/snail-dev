#!/bin/bash
#export LANG=zh_CN.gbk

EXE_NAME=${artifactId}

if [ "$1" == "" ] 
then 
        JAVA_HOME=/usr/java/jdk1.8.0_45
else 
        JAVA_HOME=$1;
fi

#JAVA_OPTS="-Dcom.sun.management.jmxremote.port=10000 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#MEM_OPTS="-d64 -Xms30g -Xmx50g -XX:PermSize=64M -XX:MaxPermSize=128m"
for PID in `ps -ef | grep -v grep |grep java | grep $EXE_NAME | awk '{print $2}'`
do kill -9 $PID
done

TIME_ZONE="-Duser.timezone=Asia/Singapore"

PRG="$0"

while [ -h "$PRG" ] ; do
        ls=`ls -ld "$PRG"`
        link=`expr "$ls" : '.*-> \(.*\)$'`
        if expr "$link" : '/.*' > /dev/null; then
                PRG="$link"
        else
                PRG=`dirname "$PRG"`/"$link"
        fi
done

PRGDIR=`dirname "$PRG"`

cp=$PRGDIR/:$PRGDIR/main.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar
for libfile in $PRGDIR/lib/*.*; do
        cp=$libfile:$cp
done

#DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=1045"
CLASSPATH=$cp

$JAVA_HOME/bin/java -D$EXE_NAME $MEM_OPTS  -classpath $cp cn.tomsnail.snail.core.starter.AppMain &