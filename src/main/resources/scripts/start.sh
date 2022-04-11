#!/bin/bash
port=9090;
serverName=spring-mysql

echo "********************************************************************************************"
mkdir -p ../logs
echo "Starting $serverName instance  environment at port [$port]..."
outfile="../logs/catalina.log"
if [ -f $outfile ];
  then
  rm $outfile
fi
touch $outfile
java -Xms1g -Xmx1g -Xmn256m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:-UseParNewGC -XX:+PrintGC -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -jar ../lib/spring-mysql*.jar --logging.config=file:../env/logback-spring.xml --spring.config.location=file:../env/application.yml > $outfile 2>&1 &


for((i=0;i<30;i++))
{
    echo -ne "."
    sleep 1
serviceCount=($(ps -fu $USER | grep -w $serverName | grep -v grep | wc -l))
if [[ $serviceCount -eq 1  ]];
then
ps -fu $USER | grep -w $serverName | grep -v grep |awk '{print "Running Service - PID ["$2"] - Start Time ["$5"] - ["$11"] - ["$12"]"}'
break
fi
}

if [[ $serviceCount -ne 1 ]];
then
ps -fu $USER | grep -w $serverName | grep -v grep |awk '{print "Running Service - PID ["$2"] - Start Time ["$5"] - ["$11"] - ["$12"]"}'
fi

echo "********************************************************************************************"