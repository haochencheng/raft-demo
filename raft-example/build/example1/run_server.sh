#!/bin/bash
if [ $# -ne 2 ]; then
    echo "Usage: ./run_server.sh DATA_PATH CLUSTER "
    exit
fi

CLUSTER=$1
CURRENT_NODE=$2

JMX_PORT=18101
GC_LOG=./logs/gc.log
#jvm config
JAVA_BASE_OPTS=" -Djava.awt.headless=true -Dfile.encoding=UTF-8 "

#JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote \
#-Dcom.sun.management.jmxremote.port=$JMX_PORT \
#-Dcom.sun.management.jmxremote.ssl=false \
#-Dcom.sun.management.jmxremote.authenticate=false "
JAVA_JMX_OPTS=""

JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -Xmn400m -XX:PermSize=128m \
-XX:MaxPermSize=128m -Xss256K \
-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled \
-XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m \
-XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly \
-XX:CMSInitiatingOccupancyFraction=70 "

JAVA_GC_OPTS=" -verbose:gc -Xloggc:$GC_LOG \
-XX:+PrintGCDetails -XX:+PrintGCDateStamps "

JAVA_OPTS=" $JAVA_BASE_OPTS $JAVA_MEM_OPTS $JAVA_JMX_OPTS $JAVA_GC_OPTS "
RAFT_SERVER_JAR=raft-server-1.0-SNAPSHOT.jar

RUNJAVA="$JAVA_HOME/bin/java"
JAVA_JAR=" -jar $RAFT_SERVER_JAR"
$RUNJAVA $JAVA_JAR $DATA_PATH $CLUSTER $CURRENT_NODE
