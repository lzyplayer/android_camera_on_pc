package com.imageTransfer;

/**
 * Created by vickyLzy on 2019/4/30
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
//客户端：上传图片
public class CUploadPhotoClient {

    public static void main(String[] args) throws Exception{
        //1.连接诶服务器
        Socket s = new Socket("127.0.0.1",5612);
        System.out.println("已连接到服务器5612端口，准备传送图片...");
        //获取图片字节流
        FileInputStream fis = new FileInputStream("./res/chihaya01.jpg");
        //获取输出流
        OutputStream out = s.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        //2.往输出流里面投放数据
        while ((len = fis.read(buf)) != -1)
        {
            out.write(buf,0,len);
        }
        //通知服务端，数据发送完毕
        s.shutdownOutput();
        //3.获取输出流，接受服务器传送过来的消息“上传成功”
//        InputStream in = s.getInputStream();
//        byte[] bufIn = new byte[1024];
//        int num = in.read(bufIn);
//        System.out.println(new String(bufIn,0,num));
        //关闭资源
        fis.close();
        out.close();
//        in.close();
        s.close();
    }
}