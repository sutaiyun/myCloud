package myCloud.work;

import myCloud.common.AppConfig;
import myCloud.common.MyDigest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class WorkSystem {
    private Logger log = LogManager.getLogger(WorkSystem.class);
    WorkConfig workConfig = new WorkConfig();

    public static WorkSystem instance = new WorkSystem();

    public void Start() {
        hello();
        workConfig.loadAppConfig("/../../config/application.properties");
        log.info("{} start ......", workConfig.appName);
    }

    public void Stop() {
        log.info("{} stop ......", workConfig.appName);
    }
    private  void hello() {
        /*
        System.out.print("This is Worker main!!!!!!");
        System.out.println("");
        System.out.println("****************************************");
        System.out.println("**                                    **");
        System.out.println("**                                    **");
        System.out.println("**               Note:                **");
        System.out.println("**         Shutdown: cmd: exit!       **");
        System.out.println("**                                    **");
        System.out.println("****************************************");
        System.out.println("");
        */

        log.info("This is Worker main!!!!!!");
        log.info("");
        log.info("****************************************");
        log.info("**                                    **");
        log.info("**                                    **");
        log.info("**               Note:                **");
        log.info("**         Shutdown: cmd: exit!       **");
        log.info("**                                    **");
        log.info("****************************************");
        log.info("");
    }

    void testSystem() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info("MD5(baidu): " + MyDigest.md5("baidu"));
        log.info("SHA(baidu): " + MyDigest.sha("baidu"));
        log.info("SHA-1(baidu): " + MyDigest.sha1("baidu"));
        log.info("MD5(百度): " + MyDigest.md5("百度"));
        log.info("SHA(百度): " + MyDigest.sha("百度"));
        log.info("SHA-1(百度): " + MyDigest.sha1("百度"));
        log.info("work home: " + myCloud.common.Util.workHome());
    }
}
