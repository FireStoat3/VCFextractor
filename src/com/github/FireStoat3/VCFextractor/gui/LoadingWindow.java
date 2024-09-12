package com.github.FireStoat3.VCFextractor.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.io.Closeable;
import java.io.IOException;
import com.github.FireStoat3.VCFextractor.VcfReader;

public class LoadingWindow implements Closeable {

    //parameters
    private Frame loadingFrame;
    private JLabel loadingInfo;
    private JProgressBar progressBar;
    private VcfReader reader;

    public LoadingWindow(VcfReader reader,String labelText) throws IOException
    {
        this.reader=reader;
        loadingFrame=new Frame("VcfExtractor",500,250);
        loadingFrame.setResizable(false);
        loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //set up label
        loadingInfo=new JLabel(labelText);
        loadingInfo.setBounds(0,0,500,50);

        //set up progress bar
        progressBar=new JProgressBar();
        progressBar.setBounds(20,60,450,90);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setMaximum(100);

        //set up frame
        loadingFrame.add(loadingInfo);
        loadingFrame.add(progressBar);
        loadingFrame.setLayout(null);
        loadingFrame.setVisible(true);
        
        //read file content
        reader.readFile(progressBar);
    }

    public LoadingWindow(VcfReader reader,String labelText,String outputFileName) throws IOException
    {
        this.reader=reader;
        loadingFrame=new Frame("VcfExtractor",500,250);
        loadingFrame.setResizable(false);
        loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //set up label
        loadingInfo=new JLabel(labelText);
        loadingInfo.setBounds(0,0,500,50);

        //set up progress bar
        progressBar=new JProgressBar();
        progressBar.setBounds(20,60,450,90);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setMaximum(100);

        //set up frame
        loadingFrame.add(loadingInfo);
        loadingFrame.add(progressBar);
        loadingFrame.setLayout(null);
        loadingFrame.setVisible(true);
        
        //read file content
        reader.writeOutputFile(outputFileName, progressBar);
    }

    public void close()
    {
        loadingFrame.dispose();
    }
    
}
