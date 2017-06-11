package com.shyammalani.demo.restapi.model;

/**
 * “Unpublished Work © 2017 Deere & Company. All Worldwide Rights Reserved. THIS MATERIAL IS THE PROPERTY OF DEERE & COMPANY. ALL USE, ALTERATIONS, DISCLOSURE, DISSEMINATION AND/OR REPRODUCTION NOT SPECIFICALLY AUTHORIZED BY DEERE & COMPANY IS PROHIBITED. “
 */
public class Criteria {
    private Integer ageGT;
    private Integer ageLT;
    private String[] favoriteColors;

    public Integer getAgeGT() {
        return ageGT;
    }

    public void setAgeGT(Integer ageGT) {
        this.ageGT = ageGT;
    }

    public Integer getAgeLT() {
        return ageLT;
    }

    public void setAgeLT(Integer ageLT) {
        this.ageLT = ageLT;
    }

    public String[] getFavoriteColors() {
        return favoriteColors;
    }

    public void setFavoriteColors(String[] favoriteColors) {
        this.favoriteColors = favoriteColors;
    }
}
