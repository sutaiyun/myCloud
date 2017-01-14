package myCloud.common;

import com.google.common.base.Strings;
import myCloud.common.msg.MyMsg;
import myCloud.common.msg.MyMsgID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;

/**
 * Created by sutaiyun on 2017/1/6.
 */
abstract public class CmdShell {
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

    protected abstract boolean cmdProcessChild(Console console, String cmd);

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

        if (cmd.equals("client")) {
            clientToServer(console);
        }

        exitProgram = cmdProcessChild (console, cmd);

        return exitProgram;
    }

    private void clientToServer(Console console) {
        String hostCmd = console.readLine("[cmd shell]# input HOST(default:localhost):");
        String portCmd = console.readLine("[cmd shell]# input PORT(default:8080):");

        cmdShellLog.info("hostCmd {}", hostCmd);
        if (hostCmd.equals("") || null == hostCmd || "\b\r" == hostCmd) {
            hostCmd = "localhost";
        }
        if (portCmd.equals("") || null == portCmd || "\b\r" == portCmd) {
            portCmd = "8080";
        }

        final String host = hostCmd;
        final String port = portCmd;

        try {
            new Thread() {
               public void run () {
                   try {
                       MyMsg msg = new MyMsg();
                       msg.setSerialNo(Util.getSerialNo());
                       msg.setMsgType(MyMsg.REQUEST_TYPE);
                       msg.setMsgID(MyMsgID.MY_REQUEST);
                       msg.setMsgLen(0x12345678);
                       msg.setPayload("{\"su\":\"xia\"");

                       HttpClient httpClient = new HttpClient();
                       httpClient.connect(host, Integer.parseInt(port), msg.encode());
                   } catch (Exception e) {
                       cmdShellLog.error("CmdShell>client error {}", e);
                   }
               }
            }.start();
        } catch (Exception e) {
            cmdShellLog.error("CmdShell>client error {}", e);
        }
    }

    private void Help() {
        System.out.println("****************************************************");
        System.out.println("CMD list:                                           ");
        System.out.println("    help, h, Help, ?    :    user help              ");
        System.out.println("    Stop                :    Stop System            ");
        System.out.println("****************************************************");
    }
}
