package myCloud.common;

import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class CmdShell {
    //private static final Logger cmdShellLog = LogManager.getLogger(CmdShell.class);
    private static final Logger cmdShellLog = LogManager.getLogger("CmdShell");
    Console console = null;

    public void Start() {
        cmdShellLog.info("CmdShell Start ..................................");
        Console console = System.console();

        while (true) {
            String cmd = console.readLine("[cmd shell]#");
            cmdShellLog.info("#" + cmd);

            if (cmdProcess(console, cmd)) return;
        }
    }

    private boolean cmdProcess(Console console, String cmd) {
        Boolean exitProgram = false;

        if (Strings.isNullOrEmpty(cmd)) {
            System.out.println("#cmd are null!");
        }

        if ("Stop" == cmd) {
            cmdShellLog.info("CmdShell Stop String ..................................");
            exitProgram = true;
            return exitProgram;
        }

        if (cmd.equals("Stop")) {
            System.out.println("You will exit !!!!!! Are you sure ????????? (yes/no)");
            String sureCmd = console.readLine("[cmd shell]#");
            if (sureCmd.equals("yes")) {
                exitProgram = true;
                cmdShellLog.info("CmdShell Stop ..................................");
            }
            return exitProgram;
        }

        if (cmd.equals("help") || cmd.equals("h") || cmd.equals("H") || cmd.equals("Help") || cmd.equals("?")) {
            Help();
        }
        return exitProgram;
    }

    private void Help() {
        System.out.println("****************************************************");
        System.out.println("CMD list:                                           ");
        System.out.println("    help, h, Help, ?    :    user help              ");
        System.out.println("    Stop                :    Stop System            ");
        System.out.println("****************************************************");
    }
}
