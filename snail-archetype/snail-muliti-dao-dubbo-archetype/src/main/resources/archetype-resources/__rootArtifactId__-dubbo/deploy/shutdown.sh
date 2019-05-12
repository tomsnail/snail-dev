#export LANG=zh_CN.gbk

EXE_NAME=${artifactId}

for PID in `ps -ef | grep -v grep |grep java | grep $EXE_NAME | awk '{print $2}'`
do kill -9 $PID
done