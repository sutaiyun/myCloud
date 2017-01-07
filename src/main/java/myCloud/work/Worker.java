package myCloud.work;

import myCloud.common.CmdShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class Worker {
    private static final Logger log = LogManager.getLogger(Worker.class);

    public static void main(String args[]) {
        log.info("Work start ..........................................");
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


        CmdShell cmdShell = new CmdShell();
        cmdShell.Start();
        log.info("Work Stop ..........................................");
    }
}
