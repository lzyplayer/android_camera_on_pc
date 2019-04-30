package com.tcpAgent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by vickyLzy on 2019/4/30
 */

public class TCPListener {
    private int port;
    private String currIpAddress;

    public int getPort() {
        return port;
    }

    public String getCurrIpAddress() {
        return currIpAddress;
    }

    public TCPListener(int port) throws UnknownHostException {
        this.port = port;
        String currNetConfig  = InetAddress.getLocalHost().getHostAddress();
        System.out.println("currNetConfig: "+currNetConfig);
        this.currIpAddress = currNetConfig;
    }


}
