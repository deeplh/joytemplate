echo "backup springboot application"
cur_date=`date +%Y-%m-%d,%H%m%s`
echo $cur_date

cp /opt/jar/bcwms-0.0.1-SNAPSHOT.jar /opt/jar/bcwms-0.0.1-SNAPSHOT-$cur_date.jar

cp /opt/jar/target/bcwms-0.0.1-SNAPSHOT.jar /opt/jar/bcwms-0.0.1-SNAPSHOT.jar

echo "stopping springboot application"
pid=`ps -ef | grep bcwms-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
echo "pid"
echo $pid
if [ -n "$pid" ]
then
echo "kill"
kill -9 $pid
fi


echo "starting springBoot application"


/opt/java/jdk/jdk1.8.0_181/bin/java -jar /opt/jar/bcwms-0.0.1-SNAPSHOT.jar --server.port=8888
