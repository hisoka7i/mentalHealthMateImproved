package com.demo.mhm.dto;

import java.sql.Timestamp;

public class UserTrackingDTO {
    private String uname;
    private Timestamp userIncomingtime;
    private String url;
    private String userAgent;
    private String ipAddress;

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public Timestamp getUserIncomingtime() {
        return userIncomingtime;
    }
    public void setUserIncomingtime(Timestamp userIncomingtime) {
        this.userIncomingtime = userIncomingtime;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Override
    public String toString() {
        return "UserTrackingDTO [uname=" + uname + ", userIncomingtime=" + userIncomingtime + ", url=" + url
                + ", userAgent=" + userAgent + ", ipAddress=" + ipAddress + "]";
    }
}
