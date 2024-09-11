package com.github.FireStoat3.VCFextractor;

public class Address 
{

    //private members
    private String state;
    private String street;
    private String city;
    private String zip;
    private String hn;

    //methods
    public Address()
    {
        this.state="unknown";
        this.street="unknown";
        this.city="unknown";
        this.zip="unknown";
        this.hn="unknown";
    }
    public Address(String state,String street,String city,String zip,String hn)
    {
        this.state=state;
        this.street=street;
        this.city=city;
        this.zip=zip;
        this.hn=hn;
    }

    public String getCompleteAddress()
    {
        StringBuilder addressStringBuilder=new StringBuilder(state.length()+street.length()+city.length()+zip.length()+hn.length());
        addressStringBuilder.append(street).append(" ").append(hn).append(" ").append(city).append(" ").append(zip).append(" ").append(state);
        return addressStringBuilder.toString();
    }

    public String getStreet()
    {
        //Return the street
        return street;
    }

    public String getHn()
    {
        //Return the House Number
        return hn;
    }

    public String getCity()
    {
        //Return the city
        return city;
    }

    public String getZip()
    {
        //Return the zip code
        return zip;
    }

    public String getState()
    {
        //Return the state
        return state;
    }

    public String setNewAddress(String street,String hn,String city,String zip,String state)
    {
        //Set a new address and return a string containing the new address
        this.state=state;
        this.street=street;
        this.city=city;
        this.zip=zip;
        this.hn=hn;
        return getCompleteAddress();
    }
}
