package com.tcpAgent;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vickyLzy on 2019/4/30
 */

public class TCPListener {
    private int port;
    private String currIpAddress;
    private ServerSocket serverSocket;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    public int getPort() {
        return port;
    }

    public String getCurrIpAddress() {
        return currIpAddress;
    }

    public TCPListener(int port) throws IOException {
        this.port = port;
        String currNetConfig  = InetAddress.getLocalHost().getHostAddress();
        System.out.println("currNetConfig: "+currNetConfig);
        this.currIpAddress = currNetConfig;
        serverSocket = new ServerSocket(this.port);


    }
    public ImageIcon receivingImage() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println(df.format(new Date())+": get a tcp request");
        //收图片
        InputStream inputStream = socket.getInputStream();
        //缓存图片
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len = 0;
        //往字节流里写图片数据
        while ((len = inputStream.read(buf)) != -1)
        {
            byteArrayOutputStream.write(buf,0,len);
        }
        inputStream.close();
        socket.close();
        byte [] imageArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        Image image = this.toolkit.createImage(imageArray);
        System.out.println(df.format(new Date())+": successfully get pic from Request");
        return new ImageIcon(image);

    }



}
