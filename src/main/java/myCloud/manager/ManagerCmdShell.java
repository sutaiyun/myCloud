package myCloud.manager;

import myCloud.common.CmdShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;

/**
 * Created by sutaiyun on 2017/1/11.
 */
public class ManagerCmdShell extends CmdShell {
    private final static Logger log = LogManager.getLogger(ManagerCmdShell.class);
    @Override
    protected boolean cmdProcessChild(Console console, String cmd) {
        Boolean exitProgram = false;

        if (cmd.equals("ManagerMsgQue")) {
            log.info("WorkMsgQue statics: size: {}", ManagerMsgQue.instance.size());
        }

        return exitProgram;
    }
}

