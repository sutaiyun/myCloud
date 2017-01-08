package myCloud.common;

/**
 * Created by sutaiyun on 2017/1/6.
 */
public class Util {
    public static String workHome() {
        String workHome = System.getProperty("application.home");
        if (null == workHome) {
            workHome = System.getProperty("user.dir");
        }

        if (null != workHome) {
            workHome = workHome.replace("\\", "/");
        }

        return workHome;
    }

    public static void main(String args[]) {
        System.out.print("This is common Util!!!!");
    }

}
