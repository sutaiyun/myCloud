package myCloud.work;

import myCloud.common.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class Worker {
    private static final Logger log = LogManager.getLogger(Worker.class);

    public static void main(String args[]) {
        try {
            WorkSystem.instance.Start();

            CmdShell cmdShell = new CmdShell();
            cmdShell.Start();

            WorkSystem.instance.Stop();
        } catch (Exception e) {
            String errorString = new StringBuffer().append("** Start work ERROR!").append(e).toString();
            log.error(errorString);
            System.exit(1);
        } finally {
            System.exit(0);
        }
    }
}
