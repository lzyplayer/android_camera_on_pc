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
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.pink);
        GridBagConstraints bagConstraints = new GridBagConstraints();
        //
        bagConstraints.gridx=0;
        bagConstraints.gridy=0;
        bagConstraints.fill=GridBagConstraints.HORIZONTAL;
        JLabel jLabel1 = new JLabel("current IP: ");
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        panel.add(jLabel1,bagConstraints);

        bagConstraints.gridx=0;
        bagConstraints.gridy=1;
        bagConstraints.fill=GridBagConstraints.HORIZONTAL;
        JLabel jLabel2 = new JLabel("listening to port :");
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        panel.add(jLabel2,bagConstraints);

        bagConstraints.gridx=1;
        bagConstraints.gridy=0;
        bagConstraints.fill=GridBagConstraints.HORIZONTAL;
        JTextField jTextField1 = new JTextField(inUDPListener.getCurrIpAddress());
        jTextField1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jTextField1,bagConstraints);

        bagConstraints.gridx=1;
        bagConstraints.gridy=1;
        bagConstraints.fill=GridBagConstraints.HORIZONTAL;
        JTextField jTextField2 = new JTextField(Integer.toString(inUDPListener.getPort()));
        jTextField2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jTextField2,bagConstraints);

        bagConstraints = new GridBagConstraints();
        bagConstraints.gridy=2;
        bagConstraints.ipady=200;
        bagConstraints.ipadx=300;
        bagConstraints.gridwidth=GridBagConstraints.REMAINDER;
//        bagConstraints.gridy=2;
        bagConstraints.fill=GridBagConstraints.BOTH;
        JTextField jTextField3 = new JTextField(Integer.toString(inUDPListener.getPort()));
        jTextField3.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jTextField3,bagConstraints);



        return panel;
    }
}
