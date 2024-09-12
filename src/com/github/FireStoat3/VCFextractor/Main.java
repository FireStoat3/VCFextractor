package com.github.FireStoat3.VCFextractor;

import com.github.FireStoat3.VCFextractor.gui.Frame;
import com.github.FireStoat3.VCFextractor.gui.MainPanel;

public class Main {
    public static void main(String args[])
    {
        Frame mainFrame=new Frame("VCF Extractor",270,350);
        mainFrame.add(new MainPanel(new VcfReader()));
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
    
}
