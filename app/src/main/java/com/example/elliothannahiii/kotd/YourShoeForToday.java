package com.example.elliothannahiii.kotd;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourShoeForToday extends BaseActivity {

    private int randomShoeInt;
    Random randomNum = new Random();
    private KOTDDBHandler kotddbHandler;
    private String weatherForToday;
    private String shoeName;

    @BindView(R.id.hand) ImageView hand;
    @BindView(R.id.showname) TextView showName;
    @BindView(R.id.textView6) TextView title;
    @BindView(R.id.gohome) Button goHome;
    @BindView(R.id.twitter) ImageButton twitter;
    @BindView(R.id.instagram) ImageButton instagram;
    @BindView(R.id.facebook) ImageButton facebook;

    @OnClick(R.id.gohome)
    public void gohome(){
        if (goHome.getText().toString() == "WEAR THESE"){
            toActivity(MainActivity.class);
            toastLong("Shoes Worn");
        }else{
            toActivity(MainActivity.class);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_shoe_for_today);
        ButterKnife.bind(this);
        handAnimator();

        kotddbHandler = KOTDDBHandler.getInstance(this, null, null, 1);
        Cursor shoeRows = kotddbHandler.countRows();
        weatherForToday = PickShoeActivity.weatherForToday;
        if (shoeRows.getCount() < 4){
            hand.setVisibility(View.GONE);
            showName.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            instagram.setVisibility(View.GONE);
            twitter.setVisibility(View.GONE);
            facebook.setVisibility(View.GONE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need to add more shoes bro.");
            builder.setPositiveButton("Add Shoes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    toActivity(ShoeNameActivity.class);
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    toActivity(MainActivity.class);
                }
            });
            builder.show();
        }
        else{
            Cursor getShoe = kotddbHandler.getShoeByWeather(weatherForToday);
            if (getShoe.getCount() == 0){

            }else{
                while (getShoe.moveToNext()){
                    shoeName = getShoe.getString(getShoe.getColumnIndex("shoeName"));
                    showName.setText(shoeName);
                    String text = showName.getText().toString();
                    if (text != shoeName){
                        instagram.setVisibility(View.GONE);
                        twitter.setVisibility(View.GONE);
                        facebook.setVisibility(View.GONE);
                    }else{
                        goHome.setText("WEAR THESE");
                    }
                }
            }
        }
    }

    public void handAnimator(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(hand, "translationY", 15, 90); //translationY
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(1000);
        animator.start();
    }
}
