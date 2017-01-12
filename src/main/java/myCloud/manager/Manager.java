package myCloud.manager;

import myCloud.common.CmdShell;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class Manager {
    public static void main(String args[]) {
        System.out.print("This is Manager main!!!!!!");

        ManagerCmdShell managerCmdShell = new ManagerCmdShell();
        managerCmdShell.Start();
    }
}
