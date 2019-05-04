package com.imageTransfer;

/**
 * Created by vickyLzy on 2019/4/30
 */
import  java.awt.*;
import  java.awt.event.*;
class  ImageCanvas  extends  Canvas
        {
                Image  image=null;
                {
                        setSize(200,200);
                }
                public  void  paint(Graphics  g){
                        if(image!=null)
                        {
                                g.drawImage(image,0,0,this);
                        }
                        else
                        {
                                this.image=image;
                        }
                }
                public  void  setImage(Image  image)
                {
                        this.image=image;
                }
                
        }