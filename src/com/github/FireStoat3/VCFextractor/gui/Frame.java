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
