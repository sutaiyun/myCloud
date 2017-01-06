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
        java -jar common-1.0-SNAPSHOT.jar

