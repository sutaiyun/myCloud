package myCloud.work;

import myCloud.common.MyDigest;
import myCloud.common.msg.MyMsg;
import myCloud.work.network.WorkHttpService;
import myCloud.work.network.WorkWebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class WorkSystem {
    private Logger log = LogManager.getLogger(WorkSystem.class);
    public final static WorkerConfig workConfig = new WorkerConfig();
    public final static WorkerMsgQueProcess workerMsgQueProcess = new WorkerMsgQueProcess();

    public static WorkSystem instance = new WorkSystem();

    public void Start() {
        hello();
        workConfig.loadAppConfig("/../../config/application.properties");
        log.info("{} start ......", workConfig.appName);

        initWorkMsgQue();
        initWorkHttpHandler();
        initWorkWebServiceHandler();

        testSystem();
    }

    private void initWorkMsgQue() {
        try {
            workerMsgQueProcess.start();
        } catch (Exception e) {
            log.error("workMsgQueProcess.start Error. {}", e);
        }
    }

    private void initWorkHttpHandler() {
        try {
            new Thread() {
                public void run () {
                    try {
                        WorkHttpService service = new WorkHttpService();
                        service.start();
                    } catch (Exception e) {
                        log.error("WorkHttpService error: {}", e);
                    }
                }
            }.start();
        } catch (Exception e) {
            log.error("initWorkHttpService error: {}", e);
        }
    }

    private void initWorkWebServiceHandler() {
        try {
            new Thread() {
                public void run () {
                    try {
                        WorkWebService service = new WorkWebService();
                        service.start();
                    } catch (Exception e) {
                        log.error("WorkWebServiceHandler error: {}", e);
                    }
                }
            }.start();
        } catch (Exception e) {
            log.error("initWorkWebServiceHandler error: {}", e);
        }
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

    void testSystem() {
        try {
            testMyDigest();
        } catch (NoSuchAlgorithmException e) {
            log.error("testMyDigest Error:{}", e);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException:{}", e);
        }
        log.info("work home: " + myCloud.common.Util.workHome());
        testMsg();
    }

    private void testMyDigest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info("MD5(baidu): " + MyDigest.md5("baidu"));
        log.info("SHA(baidu): " + MyDigest.sha("baidu"));
        log.info("SHA-1(baidu): " + MyDigest.sha1("baidu"));
        log.info("MD5(百度): " + MyDigest.md5("百度"));
        log.info("SHA(百度): " + MyDigest.sha("百度"));
        log.info("SHA-1(百度): " + MyDigest.sha1("百度"));
    }

    private void testMsg() {
        MyMsg msg = new MyMsg();
        msg.setSerialNo("1");
        msg.setMsgType("REQ");
        msg.setMsgID("ffffffff");
        msg.setMsgLen("0x12345678");
        msg.setPayload("{}");

        String jsonString = msg.encode();
        log.info("jsongString:{}", jsonString);

        MyMsg msg1 = new MyMsg();
        msg1.decode(jsonString);
        String jsonString1 = msg1.encode();
        log.info("jsongString1:{}", jsonString1);
    }
}
