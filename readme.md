### myCloud project

###### mvn profile use:
        mvn package -P local
###### run
        java -classpath common-1.0-SNAPSHOT.jar myCloud.common.Util
        java -classpath manager-1.0-SNAPSHOT.jar myCloud.manager.Util
        java -classpath work-1.0-SNAPSHOT.jar myCloud.work.Util

        java -cp common-1.0-SNAPSHOT.jar myCloud.common.Util
###### java -jar
        pom set mainClass
        java -jar manager-1.0-SNAPSHOT.jar
        windows:
        java -Djava.ext.dirs=../lib -Dfile.encoding=UTF-8  -cp .;../../config;manager-1.0-SNAPSHOT.jar myCloud.manager.Util
        linux:
        java -Djava.ext.dirs=../lib -Dfile.encoding=UTF-8  -cp .:../../config:manager-1.0-SNAPSHOT.jar myCloud.manager.Util

        指定 log4j2.xml
        java -Djava.ext.dirs=../lib -Dfile.encoding=UTF-8 -Dlog4j.configurationFile=./log4j2.xml  -cp .;../../config;worker-1.0-SNAPSHOT.jar myCloud.work.Worker
        不指定 log4j2.xml， 指定config目录即可（加入classpath目录）
        java -Djava.ext.dirs=../lib -Dfile.encoding=UTF-8 -cp .;../../config;worker-1.0-SNAPSHOT.jar myCloud.work.Worker

###### log4j core
        https://mvnrepository.com/artifact/log4j/log4j
        https://mvnrepository.com/artifact/org.apache.logging.log4j
        https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.7

        http://logging.apache.org/log4j/2.x/manual/configuration.html     log4j  configuration

