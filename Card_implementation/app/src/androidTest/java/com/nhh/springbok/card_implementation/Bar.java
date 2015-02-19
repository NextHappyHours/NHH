package com.nhh.springbok.card_implementation.Model;

import java.util.Date;

/**
 * Created by Kevin on 17-02-15.
 */
public class Bar {

    private int id;
    private String name;
    private String image;
    private String address;
    private Date hour;

    public Bar(){
    }

    public Bar(int id, String name, String image, String address, Date hour){
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.hour = hour;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public Date getHour() {
        return hour;
    }
}