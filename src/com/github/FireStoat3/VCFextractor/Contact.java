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
