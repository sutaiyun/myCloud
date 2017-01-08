package myCloud.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Properties;

import static myCloud.common.Util.workHome;

/**
 * Created by sutaiyun on 2017/1/7.
 */
public class AppConfig extends Properties {
    private static final Logger log = LogManager.getLogger(AppConfig.class);

    public String appName;
    public BigInteger logLength;

    public boolean loadAppConfig(String fileName) {
        try {
            this.clear();
            this.load(new FileInputStream(workHome() + fileName));

            logAppConfig();
        } catch (FileNotFoundException e) {
            log.error("loadAppConfig :" + e);
        } catch (IOException e) {
            log.error("loadAppConfig :" + e);
        }

        this.appName = this.getProperty("AppName");
        this.logLength = new BigInteger(this.getProperty("LogLength"));
        return true;
    }

    private  void logAppConfig() {
        for (Enumeration<?> e = this.propertyNames();
                e.hasMoreElements();) {
            String key = (String) e.nextElement();
            log.info("Config>>>>" + key +" : " +  this.getProperty(key));
        }
    }

    public boolean saveAppConfig() {
        return true;
    }
}
