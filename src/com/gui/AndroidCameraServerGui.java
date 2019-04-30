package com.gui;

import com.tcpAgent.TCPListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by vickyLzy on 2019/4/30
 */

public class AndroidCameraServerGui {

    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel imLabel;




    public AndroidCameraServerGui(String address,int port) {


        //gui
        jFrame = new JFrame("Camera Monitor");
        jFrame.setSize(1124,768);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel set component
        jPanel = readyJPanel(address,port);
        //image gbc
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.gridy=2;
        gbc.gridx=0;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        //image test
        imLabel = getImageLable("./res/chihaya01.jpg");
        jPanel.add(imLabel,gbc);
        //addJpanel
        jFrame.add(jPanel);
    }


    public  JPanel readyJPanel(String address,int port){
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
        JTextField jTextField1 = new JTextField(address);
        jTextField1.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jTextField1,bagConstraints);

        bagConstraints.gridx=1;
        bagConstraints.gridy=1;
        bagConstraints.fill=GridBagConstraints.HORIZONTAL;
        JTextField jTextField2 = new JTextField(Integer.toString(port));
        jTextField2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(jTextField2,bagConstraints);

//        bagConstraints.gridx=0;
//        bagConstraints.gridy=3;
//        bagConstraints.fill=GridBagConstraints.REMAINDER;
//        JButton jb = new JButton();
//        jb.addActionListener(e -> {
//            try {
//                imLabel.setIcon(tcpListener.receivingImage());
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
////                imLabel.setIcon(new ImageIcon("./res/chihaya02.jpg"));
//        });
//        panel.add(jb,bagConstraints);

        return panel;
    }
    public  void setCurrIcon(ImageIcon currPic){
        imLabel.setIcon(currPic);
    }
    public  void  showJFrame(){
        jFrame.setVisible(true);
    }
    public  JLabel getImageLable(String thePath){
        JLabel imageContaniner =new JLabel();
        ImageIcon pic = new ImageIcon(thePath);
        imageContaniner.setIcon(pic);
        return imageContaniner;
    }
}
//        JLabel imLable02 = getImageLable("./res/chihaya02.jpg");
//        jPanel.remove(imLable01);
//        jPanel.add(imLable02,gbc);