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

import com.github.FireStoat3.VCFextractor.VcfReader;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainPanel extends JPanel implements ActionListener{
    
    private JLabel title;
    private JButton selectButton;
    private JButton readButton;
    private JButton exportButton;
    private JLabel pathLabel;
    private VcfReader reader; 

    public MainPanel(VcfReader reader)
    {
        //Set up reader for event
        this.reader=reader;
        //Set up panel components

        //set up label
        this.title=new JLabel("Select VCF file and read its data");
        this.pathLabel=new JLabel("No file selected");
        this.title.setBounds(0,0,240,20);
        this.pathLabel.setBounds(13,75,240,40);
        this.title.setHorizontalAlignment(JLabel.CENTER);

        //set up buttons
        this.selectButton=new JButton("Select VCF file");
        this.readButton=new JButton("Read File");
        this.exportButton=new JButton("Save");

        selectButton.addActionListener(this);
        readButton.addActionListener(this);
        exportButton.addActionListener(this);

        selectButton.setBounds(13,30,240,40);
        readButton.setBounds(13,120,240,40);
        exportButton.setBounds(13,170,240,40);
        readButton.setEnabled(false);
        exportButton.setEnabled(false);

        //adding elements
        this.add(this.title);
        this.add(this.selectButton);
        this.add(this.pathLabel);
        this.add(this.readButton);
        this.add(this.exportButton);

        this.setBounds(0,0,270,400);
        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==selectButton)
        {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("VCF files", "vcf"));
            int isFileSelected=fileChooser.showOpenDialog(null);
            if(isFileSelected==JFileChooser.APPROVE_OPTION)
            {
                reader.clear();
                reader.setFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                pathLabel.setText("Selected file: "+String.format("%n")+fileChooser.getSelectedFile().getName());
                readButton.setEnabled(true);
                exportButton.setEnabled(false);
            }
            else
            {
                reader.setFilePath("unknown");
                pathLabel.setText("No file selected");
                readButton.setEnabled(false);
                exportButton.setEnabled(false);
            }
        }

        if(e.getSource()==readButton)
        {
            this.setVisible(false);
            try(LoadingWindow lw=new LoadingWindow(reader,"Reading contacts,please wait...");)
            {
                exportButton.setEnabled(true);
            }
            catch(IOException ioe)
            {
                exportButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error reading file: wrong or corrupted file!","Error reading file", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                this.setVisible(true);
            }
        }

        if(e.getSource()==exportButton)
        {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int isFileSelected=fileChooser.showSaveDialog(null);
            if(isFileSelected==JFileChooser.APPROVE_OPTION)
            {
                this.setVisible(false);
                reader.setOutputFilePath(fileChooser.getSelectedFile().getParent()+"/");
                try(LoadingWindow lw=new LoadingWindow(reader,"Writing data, please wait...",fileChooser.getSelectedFile().getName()))
                {
                    JOptionPane.showMessageDialog(null, "File has been saved in "+fileChooser.getSelectedFile().getAbsolutePath()+String.format("%n")+"You may need to add the file type (.txt)","Save", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException ioe)
                {
                    JOptionPane.showMessageDialog(null, "Error while saving data, please try again.","Error saving file", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    this.setVisible(true);
                }
            }
        }
    }

}
