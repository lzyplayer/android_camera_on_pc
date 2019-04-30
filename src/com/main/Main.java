package com.main;

import com.gui.AndroidCameraServerGui;
import com.tcpAgent.TCPListener;
import com.tcpAgent.TCPThread;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by vickyLzy on 2019/4/30
 */

public class Main {
    public static void main(String[] args) throws IOException {
        //getNet
        TCPListener myTCPListener = new TCPListener(31845);
        System.out.println("=============TCP server is running======================");
        //gui
        AndroidCameraServerGui acsg = new AndroidCameraServerGui(myTCPListener.getCurrIpAddress(),myTCPListener.getPort());
        acsg.showJFrame();
        //ThreadTCP
        TCPThread tcpThread =new TCPThread(myTCPListener,acsg);
        tcpThread.start();



    }
}
