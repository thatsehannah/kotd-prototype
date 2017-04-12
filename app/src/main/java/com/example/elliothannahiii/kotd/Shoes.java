package com.example.elliothannahiii.kotd;

/**
 * Created by elliothannahiii on 4/7/17.
 */

public class Shoes {

    private String shoeName;
    private String shoeWeather;
    private String shoeActivity;
    private String shoeFavorite;
    private int shoeID;

    public Shoes (String shoeName, String shoeWeather, String shoeActivity, String shoeFavorite){
        this.shoeName = shoeName;
        this.shoeWeather = shoeWeather;
        this.shoeActivity = shoeActivity;
        this.shoeFavorite = shoeFavorite;
    }

    public void setShoeID(int id){
        this.shoeID = id;
    }

    public void setshoeName(String _shoeName){
        this.shoeName = _shoeName;
    }

    public void setshoeWeather(String _shoeWeather){
        this.shoeWeather = _shoeWeather;
    }

    public void setshoeActivity(String _shoeActivity){
        this.shoeActivity = _shoeActivity;
    }

    public void setshoeFavorite(String _shoeFavorite){
        this.shoeFavorite = _shoeFavorite;
    }
    
    public int getShoeID(){
        return shoeID;
    }
    
    public String getshoeName(){
        return shoeName;
    }

    public String getshoeWeather() {
        return shoeWeather;
    }

    public String getshoeActivity() {
        return shoeActivity;
    }

    public String getshoeFavorite() {
        return shoeFavorite;
    }
}
