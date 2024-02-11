package edu.lakeland.mycontactlist;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Calendar;

public class Contact {

    public final String TAG = "Contacts";
    public final String DELIM = "|";

    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String cellNumber;
    private String eMail;
    private Calendar birthday;
    private Bitmap picture;

    public Contact() {
        contactID = -1;
        birthday = Calendar.getInstance();
    }

    public int getContactID() {
        return contactID;
    }
    public void setContactID(int i) {
        contactID = i;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String s) {
        contactName = s;
    }
    public Calendar getBirthday() {
        return birthday;
    }
    public void setBirthday(Calendar c) {
        birthday = c;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String s) {
        streetAddress = s;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String s) {
        city = s;
    }
    public String getState() {
        return state;
    }
    public void setState(String s) {
        state = s;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String s) {
        zipCode = s;
    }
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setCellNumber(String s) {
        cellNumber = s;
    }
    public String getCellNumber() {
        return cellNumber;
    }
    public void setEMail(String s) {
        eMail = s;
    }
    public String getEMail() {
        return eMail;
    }
    public Bitmap getPicture() { return picture; }

    public void setPicture(Bitmap picture) { this.picture = picture; }

    public String toString()
    {
        String data = contactID
                + contactName + DELIM
                + streetAddress + DELIM
                + city + DELIM
                + state + DELIM
                + zipCode + DELIM
                + phoneNumber + DELIM
                + cellNumber + DELIM
                + eMail;
        //+ birthday;

        Log.d(TAG, "toString: " + data);
        return data;
    }
}