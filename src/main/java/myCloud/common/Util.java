package myCloud.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

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

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static Integer getSerialNo() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(8));
    }

    public static void main(String args[]) {
        System.out.print("This is common Util!!!!");
        System.out.print("This is UUID " + getUUID());
    }


}
