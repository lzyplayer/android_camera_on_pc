package com.gui;

import com.udplisenter.UDPListener;

import javax.swing.*;
import java.awt.*;




public class MyAndroidCameraServerGui {

    public static void main(String[] args) {
        //currNet
        UDPListener myUDPListener = new UDPListener(31233);




        //gui
        JFrame jFrame = new JFrame("Camera Monitor");
        jFrame.setSize(800,600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel set component
        jFrame.add(readyJPanel(myUDPListener));
        jFrame.setVisible(true);
    }

    public static JPanel readyJPanel(UDPListener inUDPListener){
        JPanel panel = new JPanel(new GridLayout(3,2));
        JLabel jLabel1 = new JLabel("current IP: ");
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        JLabel jLabel2 = new JLabel("listening to port :");
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        JTextField jTextField1 = new JTextField(inUDPListener.getCurrIpAddress());
        jTextField1.setHorizontalAlignment(JTextField.CENTER);
        JTextField jTextField2 = new JTextField(Integer.toString(inUDPListener.getPort()));
        jTextField2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jLabel1);
        panel.add(jTextField1);
        panel.add(jLabel2);
        panel.add(jTextField2);
        return panel;
    }
}
