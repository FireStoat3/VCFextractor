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

package com.github.FireStoat3.VCFextractor;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JProgressBar;

public class VcfReader {
    //private parameters
    private String filePath;
    private String outputFilePath;
    private ArrayList<Contact> contacts;
    private int ncontacts;
    private boolean isReaded;

    //methods
    public VcfReader()
    {
        this.filePath="unknown";
        this.outputFilePath="unknown";
        contacts=new ArrayList<>();
        ncontacts=0;
        isReaded=false;
    }
    public VcfReader(String filePath,String outputFilePath)
    {
        this.filePath=filePath;
        this.outputFilePath=outputFilePath;
        contacts=new ArrayList<>();
        ncontacts=0;
        isReaded=false;
    }

    private int findContacts() throws FileNotFoundException
    {
        //Return the number of contacts in the file
        int ncontacts=0;
        try(Scanner fileReader=new Scanner(new File(filePath)))
        {
            String readedLine="unknown";
            while(fileReader.hasNext())
            {
                readedLine=fileReader.nextLine();
                if(readedLine.compareTo("END:VCARD")==0)
                {
                    ncontacts+=1;
                }
            }
            return ncontacts;
        }
        catch(FileNotFoundException fnfe)
        {
            return 0;
        }
    }

    public void clear()
    {
        //Delete readed data
        filePath="unknown";
        outputFilePath="unknown";
        contacts.clear();
        ncontacts=0;
        isReaded=false;
    }

    public void readFile() throws FileNotFoundException
    {
        //Read the vcf file and memorize its contacts in contacts ArrayList<Contact>
        try(Scanner fileReader=new Scanner(new File(filePath)))
        {
            String readedLine="unknown",name="unknown",address="unknown";
            ArrayList<String> number=new ArrayList<>(),email=new ArrayList<>();
            boolean addrfound=false;
            ncontacts=findContacts();
            
            while(fileReader.hasNext())
            {
                readedLine=fileReader.nextLine();
                if(readedLine.compareTo("END:VCARD")!=0)
                {
                    switch(readedLine)
                    {
                        case String s when s.contains("FN:"):
                        {
                            name=s.substring(3);
                            break;
                        }
                        case String s when s.contains("ADR"):
                        {
                            if(addrfound==false)
                            {
                                address=s.substring(s.lastIndexOf(":")+1).replace(";"," ");
                                addrfound=true;
                            }
                            break;
                        }
                        case String s when s.contains("TEL"):
                        {
                            number.add(s.substring(s.lastIndexOf(":")+1));
                            break;
                        }
                        case String s when s.contains("EMAIL"):
                        {
                            email.add(s.substring(s.lastIndexOf(":")+1));
                            break;
                        }
                        default: break;

                    }
                }
                else
                {
                    contacts.add(new Contact(name,new ArrayList<>(number),new ArrayList<>(email),address));
                    name="unknown";
                    number.clear();
                    email.clear();
                    address="unknown";
                    addrfound=false;
                }
            }
            isReaded=true;
        }
    }

    public void readFile(JProgressBar progressBar) throws FileNotFoundException
    {
        //Read the vcf file and memorize its contacts in contacts ArrayList<Contact>
        try(Scanner fileReader=new Scanner(new File(filePath)))
        {
            String readedLine="unknown",name="unknown",address="unknown";
            ArrayList<String> number=new ArrayList<>(),email=new ArrayList<>();
            boolean addrfound=false;
            ncontacts=findContacts();
            int counter=0;
            
            while(fileReader.hasNext())
            {
                readedLine=fileReader.nextLine();
                if(readedLine.compareTo("END:VCARD")!=0)
                {
                    switch(readedLine)
                    {
                        case String s when s.contains("FN:"):
                        {
                            name=s.substring(3);
                            break;
                        }
                        case String s when s.contains("ADR"):
                        {
                            if(addrfound==false)
                            {
                                address=s.substring(s.lastIndexOf(":")+1).replace(";"," ");
                                addrfound=true;
                            }
                            break;
                        }
                        case String s when s.contains("TEL"):
                        {
                            number.add(s.substring(s.lastIndexOf(":")+1));
                            break;
                        }
                        case String s when s.contains("EMAIL"):
                        {
                            email.add(s.substring(s.lastIndexOf(":")+1));
                            break;
                        }
                        default: break;

                    }
                }
                else
                {
                    contacts.add(new Contact(name,new ArrayList<>(number),new ArrayList<>(email),address));
                    name="unknown";
                    number.clear();
                    email.clear();
                    address="unknown";
                    addrfound=false;
                    counter+=1;
                    progressBar.setValue(100*counter/ncontacts);
                }
            }
            isReaded=true;
        }
    }

    public int getNcontacts()
    {
        //Return the number of contacts in the vcf file
        return ncontacts;
    }

    public ArrayList<Contact> getContacts()
    {
        //Return an ArrayList<Contact> of readed contacts
        return contacts;
    }

    public String getFilePath()
    {
        //Return current input file path
        return filePath;
    }

    public String getOutputFilePath()
    {
        //return current output file path
        return outputFilePath;
    }

    public void setFilePath(String filePath)
    {
        //set input file path to filePath
        this.filePath=filePath;
        isReaded=false;
    }

    public void setOutputFilePath(String outputFilePath)
    {
        //Set output file path to outputFilePath
        this.outputFilePath=outputFilePath;
    }

    public boolean isReaded()
    {
        //Return true if the file has been readen, false if not
        return isReaded;
    }

    public void writeOutputFile(String name) throws IOException
    {
        //Write contacts info to a file in outputFilePath named name
        File outputFile=new File(outputFilePath+name);
        outputFile.createNewFile();
        try(FileWriter ofw=new FileWriter(outputFile))
        {
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            ofw.write("EXPORTED CONTACTS"+String.format("%n"));
            ofw.write("Time and date: "+sdf.format(new Date())+String.format("%n"));
            ofw.write("Contacts:"+String.format("%n"));
            Iterator<Contact> contactsIterator=contacts.iterator();
            ArrayList<String> contactString=new ArrayList<>();
            Iterator<String> contactStringIterator=contactString.iterator();
            while(contactsIterator.hasNext())
            {
                Contact currentContact=contactsIterator.next();
                ofw.write("-- Name --"+String.format("%n"));
                ofw.write(currentContact.getName()+String.format("%n"));
                ofw.write("-- Address --"+String.format("%n"));
                ofw.write(currentContact.getAddress()+String.format("%n"));
                ofw.write("-- Phone number --"+String.format("%n"));
                contactString=currentContact.getNumber();
                contactStringIterator=contactString.iterator();
                if(contactString.isEmpty()==true)
                {
                    ofw.write("unknown"+String.format("%n"));
                }
                while(contactStringIterator.hasNext())
                {
                    ofw.write(contactStringIterator.next()+String.format("%n"));
                }
                ofw.write("-- Email --"+String.format("%n"));
                contactString=currentContact.getEmail();
                contactStringIterator=contactString.iterator();
                if(contactString.isEmpty()==true)
                {
                    ofw.write("unknown"+String.format("%n"));
                }
                while(contactStringIterator.hasNext())
                {
                    ofw.write(contactStringIterator.next()+String.format("%n"));
                }
                ofw.write(String.format("%n")+String.format("%n"));
            }
        }
        catch(IOException ioe)
        {
            throw ioe;
        }
    }
    public void writeOutputFile(String name,JProgressBar progressbar) throws IOException
    {
        //Write contacts info to a file in outputFilePath named name
        File outputFile=new File(outputFilePath+name);
        outputFile.createNewFile();
        try(FileWriter ofw=new FileWriter(outputFile))
        {
            int counter=0;
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            ofw.write("EXPORTED CONTACTS"+String.format("%n"));
            ofw.write("Time and date: "+sdf.format(new Date())+String.format("%n"));
            ofw.write("Contacts:"+String.format("%n"));
            Iterator<Contact> contactsIterator=contacts.iterator();
            ArrayList<String> contactString=new ArrayList<>();
            Iterator<String> contactStringIterator=contactString.iterator();
            while(contactsIterator.hasNext())
            {
                Contact currentContact=contactsIterator.next();
                ofw.write("-- Name --"+String.format("%n"));
                ofw.write(currentContact.getName()+String.format("%n"));
                ofw.write("-- Address --"+String.format("%n"));
                ofw.write(currentContact.getAddress()+String.format("%n"));
                ofw.write("-- Phone number --"+String.format("%n"));
                contactString=currentContact.getNumber();
                contactStringIterator=contactString.iterator();
                if(contactString.isEmpty()==true)
                {
                    ofw.write("unknown"+String.format("%n"));
                }
                while(contactStringIterator.hasNext())
                {
                    ofw.write(contactStringIterator.next()+String.format("%n"));
                }
                ofw.write("-- Email --"+String.format("%n"));
                contactString=currentContact.getEmail();
                contactStringIterator=contactString.iterator();
                if(contactString.isEmpty()==true)
                {
                    ofw.write("unknown"+String.format("%n"));
                }
                while(contactStringIterator.hasNext())
                {
                    ofw.write(contactStringIterator.next()+String.format("%n"));
                }
                ofw.write(String.format("%n")+String.format("%n"));
                counter+=1;
                progressbar.setValue(ncontacts*100/counter);
            }
        }
        catch(IOException ioe)
        {
            throw ioe;
        }
    }
}
