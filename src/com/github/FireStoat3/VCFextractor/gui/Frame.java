package com.github.FireStoat3.VCFextractor.gui;

import javax.swing.JFrame;

public class Frame extends JFrame{
    
    public Frame(String frameTitle)
    {
        this.setSize(480,480);
        this.setResizable(false);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Frame(String frameTitle,int xSize,int ySize)
    {
        this.setSize(xSize,ySize);
        this.setResizable(false);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
