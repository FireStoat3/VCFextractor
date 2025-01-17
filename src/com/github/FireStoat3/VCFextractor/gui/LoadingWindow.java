/*
*    Copyright (C) 2024
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*
*/

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

    public LoadingWindow(VcfReader reader,String labelText) throws IOException
    {

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
