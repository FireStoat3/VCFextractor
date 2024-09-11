package com.github.FireStoat3.VCFextractor;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VcfReader {
    //private parameters
    private String filePath;
    private ArrayList<Contact> contacts;
    private int ncontacts;
    private boolean isReaded;

    //methods
    public VcfReader(String filePath)
    {
        this.filePath=filePath;
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

    public boolean isReaded()
    {
        //Return true if the file has been readen, false if not
        return isReaded;
    }
}
