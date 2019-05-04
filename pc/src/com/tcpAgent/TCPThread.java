package com.tcpAgent;

import com.gui.AndroidCameraServerGui;


import java.io.IOException;

/**
 * Created by vickyLzy on 2019/5/1
 */
public class TCPThread extends Thread {
    private TCPListener tcpListener;
    private AndroidCameraServerGui acsb;
    public TCPThread(TCPListener intcpListener,AndroidCameraServerGui androidCameraServerGui) {
        System.out.println("starting a TCP listener...");
        this.tcpListener = intcpListener;
        this.acsb =androidCameraServerGui;
    }

    public  void run(){
        while (true){
            try {
                acsb.setCurrIcon(tcpListener.receivingImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
