package com.example.listycity5;

public class City {
    private String city;
    private String province;
    private String cityDocId;

    public City(String city, String province){
        this.city = city;
        this.province = province;
    }

    public City(String city, String province, String cityDocId){
        this.city = city;
        this.province = province;
        this.cityDocId = cityDocId;
    }
    public String getCityName(){
        return this.city;
    }

    public String getProvinceName(){
        return this.province;
    }

    public String getCityDocId(){
        return this.cityDocId;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCityDocId(String cityDocId) {
        this.cityDocId = cityDocId;
    }
}