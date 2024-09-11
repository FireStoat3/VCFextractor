package com.github.FireStoat3.VCFextractor;

public class Contact {

    //private parameters
    private String number;
    private String email;
    private Address address;

    //methods
    public Contact()
    {
        this.number="unknown";
        this.email="unknown";
        this.address=new Address();
    }
    public Contact(String number,String email,Address address)
    {
        this.number=number;
        this.email=email;
        this.address=address;
    }
    public Contact(String number,String email,String state,String street,String city,String zip,String hn)
    {
        this.number=number;
        this.email=email;
        this.address=new Address(state,street,city,zip,hn);
    }

    public String getNumber()
    {
        return number;
    }

    public String getEmail()
    {
        return email;
    }

    public Address getAddress()
    {
        return address;
    }
}
