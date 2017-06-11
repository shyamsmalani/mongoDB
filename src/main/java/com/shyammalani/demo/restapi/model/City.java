package com.shyammalani.demo.restapi.model;

/**
 * “Unpublished Work © 2017 Deere & Company. All Worldwide Rights Reserved. THIS MATERIAL IS THE PROPERTY OF DEERE & COMPANY. ALL USE, ALTERATIONS, DISCLOSURE, DISSEMINATION AND/OR REPRODUCTION NOT SPECIFICALLY AUTHORIZED BY DEERE & COMPANY IS PROHIBITED. “
 */
public class City {

    private String name;
    private Integer pin;

    public City() {
    }

    public City(String name, Integer pin) {
        this.name = name;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
