package myCloud.work;

import myCloud.common.CmdShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;

/**
 * Created by sutaiyun on 2017/1/11.
 */
public class WorkerCmdShell extends CmdShell {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected boolean cmdProcessChild(Console console, String cmd) {
        Boolean exitProgram = false;

        if (cmd.equals("WorkMsgQue")) {
            log.info("WorkMsgQue statics: size: {}", WorkMsgQue.instance.size());
        }

        return exitProgram;
    }
}
