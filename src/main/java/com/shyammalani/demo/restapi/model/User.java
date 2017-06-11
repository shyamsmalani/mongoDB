package com.shyammalani.demo.restapi.model;


public class User {

    private Integer id;
    private String email;
    private String mobileNum;
    private String firstName;
    private Integer age;
    private City city;
    private String[] favoriteColors;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String[] getFavoriteColors() {
        return favoriteColors;
    }

    public void setFavoriteColors(String[] favoriteColors) {
        this.favoriteColors = favoriteColors;
    }
}
