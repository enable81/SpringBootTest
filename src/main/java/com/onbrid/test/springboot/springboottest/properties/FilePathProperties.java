package com.onbrid.test.springboot.springboottest.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.file.path")
public class FilePathProperties {

    //  OS Name
    private String OS  =   System.getProperty("os.name").toLowerCase();

    private String window;
    private String mac;
    private String unix;

    @Value("${spring.profiles.active}")
    private String profile;

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUnix() {
        return unix;
    }

    public void setUnix(String unix) {
        this.unix = unix;
    }

    public String getFilePathOnPrefix(){

        if( OS.indexOf("win") != -1 ){
            return "file:\\" + window;
        }else if( OS.indexOf("mac") != -1 ){
            return "file://"+ mac;
        }else if( OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0  ){
            return "file://"+ unix;
        }else {
            return "/";
        }
    }


    public String getFilePath(){
        if (OS.indexOf("win") != -1) {
            return window;
        } else if (OS.indexOf("mac") != -1) {
            return mac;
        } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
            return unix;
        } else {
            return "/";
        }
    }

    public String getFilePath(String localPath){
        if ("local".equals(profile) ||
                "dev".equals(profile)) {
            return localPath;
        } else {
            return getFilePath();
        }
    }
}
