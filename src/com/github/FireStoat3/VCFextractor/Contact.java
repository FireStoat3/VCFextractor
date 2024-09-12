package com.github.FireStoat3.VCFextractor;

import java.util.ArrayList;

public class Contact {

    //private parameters
    private String name;
    private ArrayList<String> number;
    private ArrayList<String> email;
    private String address;

    //methods
    public Contact()
    {
        this.name="unknown";
        this.number=new ArrayList<>();
        this.email=new ArrayList<>();
        this.address="unknown";
    }
    public Contact(String name,String number,String email,String address)
    {
        this.name=name;
        this.number=new ArrayList<>();
        this.email=new ArrayList<>();
        this.number.add(number);
        this.email.add(email);
        this.address=address;
    }
    public Contact(String name,ArrayList<String> number,ArrayList<String> email,String address)
    {
        this.name=name;
        this.number=number;
        this.email=email;
        this.address=address;
    }

    public String getName()
    {
        //Return name of the contact
        return name;
    }

    public ArrayList<String> getNumber()
    {
        //Return the phone number
        return number;
    }

    public ArrayList<String> getEmail()
    {
        //Return the email
        return email;
    }

    public String getAddress()
    {
        //Return the address
        return address;
    }
}
