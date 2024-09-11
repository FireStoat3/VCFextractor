package com.github.FireStoat3.VCFextractor;

public class Main {
    public static void main(String args[])
    {
        Contact test=new Contact("123456789","test@test.com","USA","testStreet","testCity","1234","testHn");
        Address testAddress=test.getAddress();

        System.out.printf("%s\n",testAddress.getCompleteAddress());
    }
    
}
