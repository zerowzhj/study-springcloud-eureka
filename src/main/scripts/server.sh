#!/bin/bash
#解决Jenkins在构建完成后使用processTreeKiller杀掉了所有子进程的问题
export BUILD_ID=dontkillme

JAR_FILE=/app/study-springcloud-eureka/study-springcloud-eureka-1.0.jar
LOG_FILE=/app/study-springcloud-eureka/stdout.log

JAVA_MEM_OPTS=" -server -Xms512M -Xmx512M -Xmn128M -Xss128M -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=512M"
JAVA_GC_OPTS=" -XX:+PrintGC -XX:PrintGCDetails -XX:PrintGCTime"
JAVA_OPTS=$JAVA_MEM_OPTS $JAVA_GC_OPTS
#
#export JAVA_HOME
#export PATH=$PATH:$JAVA_HOME/bin

get_pid() {
  pid=$(ps -ef | grep $JAR_FILE | grep -v grep | awk '{ print $2 }')
  echo "$pid"
}
start() {
  pid=$(get_pid)
  if [ -z "$pid" ]; then
    nohup java $JAVA_OPTS -jar $JAR_FILE >$LOG_FILE 2>&1 &
  fi
}
stop() {
  pid=$(get_pid)
  if [ -n "$pid" ]; then
    kill -9 $pid
  fi
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
*)
  echo "Usage: $0 {start|stop}"
  ;;
esac
