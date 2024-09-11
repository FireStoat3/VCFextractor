package com.github.FireStoat3.VCFextractor;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String args[])
    {
        Scanner keyboardInput=new Scanner(System.in);
        System.out.printf("path: ");
        String path=keyboardInput.nextLine();
        keyboardInput.close();

        VcfReader reader=new VcfReader(path);
        try
        {
            reader.readFile();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.printf("%nFile non esistente%n");
        }

        if(reader.isReaded()==true)
        {
            ArrayList<Contact> list=reader.getContacts();
            Iterator<Contact> global_it=list.iterator();
            Contact present_contact=new Contact();
            while(global_it.hasNext()==true)
            {
                present_contact=global_it.next();
                System.out.printf("-------%n");
                System.out.printf("Name: %s%n",present_contact.getName());
                System.out.printf("Address: %s%n",present_contact.getAddress());
                System.out.printf("Number: %s%n",present_contact.getNumber());
                System.out.printf("Email: %s%n",present_contact.getEmail());
            }
            System.out.printf("-------%n");
        }
    }
    
}
