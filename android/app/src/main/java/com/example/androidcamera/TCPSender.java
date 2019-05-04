package com.example.androidcamera;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPSender {
    private String address;
    private int port;

    public TCPSender(String inaddress, int inport)  {
        this.address = inaddress;
        this.port = inport;
    }

    public void setAddress(String inaddress){
        this.address = inaddress;
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
