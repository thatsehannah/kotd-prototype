package com.example.elliothannahiii.kotd;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private SQLiteDatabase db;

    @OnClick(R.id.main_mycloset)
    public void myKicksButton(){
        toActivity(MyKicksActivity.class);
    }

    @OnClick(R.id.add_kicks)
    public void addSneakerButton(){
        toActivity(BarcodeOrManualActivity.class);
    }

    @OnClick(R.id.pick_shoe)
    public void pickSneakerForTheDay(){
        toActivity(PickShoeActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
