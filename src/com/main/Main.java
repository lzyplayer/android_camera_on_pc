package com.main;

import com.gui.AndroidCameraServerGui;
import com.tcpAgent.TCPListener;

import java.net.UnknownHostException;

/**
 * Created by vickyLzy on 2019/4/30
 */

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        //getNet
        TCPListener myTCPListener = new TCPListener(31233);
        //gui
        AndroidCameraServerGui acsg = new AndroidCameraServerGui(myTCPListener);
        acsg.showJFrame();


    }
}
