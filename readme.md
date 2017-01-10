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

### libs
###### java doc
http://docs.oracle.com/javase/6/docs/api/java/security/MessageDigest.html
http://docs.oracle.com/javase/8/

###### Guava: Google Core Libraries for Java
https://github.com/google/guava
###### MessageDigest简介 (为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。简单点说就是用于生成散列码)
http://hubingforever.blog.163.com/blog/static/171040579201210781650340/
http://docs.oracle.com/javase/6/docs/api/java/security/MessageDigest.html
###### Binary Encoders   (base32, base64, Hex, BinaryCodec...MessageDigest.:)
http://commons.apache.org/proper/commons-codec/userguide.html

http://xerces.apache.org/xerces-j/apiDocs/org/apache/xerces/utils/HexBin.html   (或者使用apache的code库）
java.lang.Object
  |
  +--org.apache.xerces.utils.HexBin
###### fastJson   smartJson
http://www.cnblogs.com/zhenjing/p/json-smart.html
https://github.com/netplex/json-smart-v2

######  Java 7中的TransferQueue
http://ifeve.com/java-transfer-queue/
http://www.68idc.cn/help/buildlang/java/2013081947502.html
http://lawrence-zxc.github.io/2011/03/14/thread-blocking/   (add, put, take, offer, poll, drainTo)

###### websocket 开发
http://blog.csdn.net/u011096030/article/details/14548211
http://blog.csdn.net/stoneson/article/details/8073285  (websocket  协议)
http://blog.csdn.net/mffandxx/article/details/52296306   Netty之HTTP协议开发
http://www.infoq.com/cn/articles/netty-version-upgrade-history-thread-part   (netty 内存池问题）
