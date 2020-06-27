#!/usr/bin bash
set -e
BIN_DIR=$(cd "$(dirname "$0")";pwd)
cd ${BIN_DIR}
#cd ../../raft-server && mvn clean -U install -DskipTests
#cd -

ROOT_DIR=./../build
mkdir -p $ROOT_DIR
cd $ROOT_DIR
echo $PWD

RAFT_SERVER_JAR=raft-server-1.0-SNAPSHOT.jar

[[ -d example1 ]] && rm -rf example1
mkdir example1
cd example1

cp -f ./../../../raft-server/target/$RAFT_SERVER_JAR .
cp -f ./../../../raft-example/bin/run_server.sh .
chmod +x *.sh
nohup run_server.sh "127.0.0.1:8051:server1,127.0.0.1:8052:server2,127.0.0.1:8053:server3" "server1" &

cd $ROOT_DIR

[[ -d example1 ]] && rm -rf example1
mkdir example1
cd example1

cp -f ./../../../raft-server/target/$RAFT_SERVER_JAR .
cp -f ./../../../raft-example/bin/run_server.sh .
chmod +x *.sh
nohup run_server.sh "127.0.0.1:8051:server1,127.0.0.1:8052:server2,127.0.0.1:8053:server3" "server2" &

cd $ROOT_DIR


[[ -d example1 ]] && rm -rf example1
mkdir example1
cd example1

cp -f ./../../../raft-server/target/$RAFT_SERVER_JAR .
cp -f ./../../../raft-example/bin/run_server.sh .
chmod +x *.sh
nohup run_server.sh "127.0.0.1:8051:server1,127.0.0.1:8052:server2,127.0.0.1:8053:server3" "server3" &

echo "raft cluster started success "
exit 0