package com.example.elliothannahiii.kotd;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.text.TextUtils;

public class WeatherAddKicksActivity extends BaseActivity {

    public static String weathernames = "";

    @BindView(R.id.cb_cold)
    CheckBox cbCold;

    @BindView(R.id.cb_hot)
    CheckBox cbHot;

    @BindView(R.id.cb_sunny)
    CheckBox cbSunny;

    @BindView(R.id.cb_rain)
    CheckBox cbRain;

    @BindView(R.id.cb_snow)
    CheckBox cbSnow;


    //////////////////////////////////////////////

    @OnClick(R.id.weather_home)
    public void backToHome(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure?");
        builder.setMessage("You will lose all of the data for this shoe!");
        builder.setPositiveButton("Bye", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toActivity(MainActivity.class);
            }
        });
        builder.setNegativeButton("Stay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @OnClick(R.id.nxt_weather)
    public void toActivityType(){
        if (cbSnow.isChecked() || cbRain.isChecked() || cbSunny.isChecked() || cbHot.isChecked() || cbCold.isChecked()){
            toActivity(WhereToAddKicksActivity.class);
        }else{
            AlertDialog.Builder noWeather = new AlertDialog.Builder(this);
            noWeather.setMessage("Please select at least 1 weather type.");
            noWeather.show();
        }
        if (cbSnow.isChecked()){
            weathernames = weathernames + " Snow";
        }else if (cbSnow.isChecked() == false){
            weathernames.replace("Snow", "");
        }

        if (cbSunny.isChecked()){
            weathernames = weathernames + " Sunny";
        }else if (cbSunny.isChecked() == false){
            weathernames.replace("Sunny", "");
        }

        if (cbRain.isChecked()){
            weathernames = weathernames + " Rain";
        }else if (cbRain.isChecked() == false){
            weathernames.replace("Rain", "");
        }

        if (cbCold.isChecked()){
            weathernames = weathernames + " Cold";
        }else if (cbCold.isChecked() == false){
            weathernames.replace("Cold", "");
        }

        if (cbHot.isChecked()){
            weathernames = weathernames + " Hot";
        }else if (cbHot.isChecked() == false){
            weathernames.replace("Hot", "");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_add_kicks);
        ButterKnife.bind(this);

    }
}
