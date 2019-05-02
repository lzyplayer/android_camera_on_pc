package com.main;

import com.tcpAgent.TCPSender;

import java.io.File;
import java.io.IOException;

/**
 * Created by vickyLzy on 2019/4/30
 */
public class SimSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        TCPSender tcpSender = new TCPSender("127.0.0.1",31845);

        File path = new File("./res/bearCat");
        File [] pics = path.listFiles();
//        System.out.println(pics[2].getPath());
        while (true) {
            for (int i = 0; i < pics.length; i++) {
                tcpSender.sendImage(pics[i].getPath());
                Thread.sleep(180);
            }
        }
//        System.out.println("send all pics");

    }
}
