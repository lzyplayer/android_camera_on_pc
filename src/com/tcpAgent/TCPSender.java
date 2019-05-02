package com.tcpAgent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by vickyLzy on 2019/5/1
 */
public class TCPSender {
    private String address;
    private int port;



    public TCPSender(String inaddress, int inport)  {
        this.address = inaddress;
        this.port = inport;
    }

    public boolean sendImage(String filePath) throws IOException {
        System.out.println("try Tcp  on "+this.address+" on port "+this.port+"...");
        Socket socket = new Socket(this.address,this.port);
        System.out.println("tcp established.");
        FileInputStream fileInputStream = new FileInputStream(filePath);
        OutputStream outputStream =socket.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buf)) != -1)
        {
            outputStream.write(buf,0,len);
        }
        fileInputStream.close();
        socket.shutdownOutput();
        outputStream.close();
        socket.close();
        return true;
    }
}
//public class TCPSender {
//    private String address;
//    private int port;
//    private Socket socket;
//
//
//    public TCPSender(String inaddress, int inport) throws IOException {
//        this.address = inaddress;
//        this.port = inport;
//        System.out.println("try Tcp  on "+inaddress+" on port "+inport+"...");
//        this.socket =new Socket(this.address,this.port);
//        System.out.println("tcp established.");
//    }
//
//    public boolean sendImage(String filePath) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        OutputStream outputStream =socket.getOutputStream();
//        byte[] buf = new byte[1024];
//        int len = 0;
//        while ((len = fileInputStream.read(buf)) != -1)
//        {
//            outputStream.write(buf,0,len);
//        }
//        fileInputStream.close();
//        outputStream.close();
//        socket.shutdownOutput();
//        return true;
//    }
//}
