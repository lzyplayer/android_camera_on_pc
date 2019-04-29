package com.udplisenter;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPListener {
    private int port;
    private String currIpAddress;

    public int getPort() {
        return port;
    }

    public String getCurrIpAddress() {
        return currIpAddress;
    }

//    public void setCurrIpAddress(String currIpAddress) {
//        this.currIpAddress = currIpAddress;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }

    public UDPListener(int port) {
        this.port = port;
        String currNetConfig = null;
        try {
            currNetConfig  = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("currNetConfig: "+currNetConfig);
        this.currIpAddress = currNetConfig;
    }
}
