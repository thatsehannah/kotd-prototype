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


public class WhereToAddKicksActivity extends BaseActivity {

    public static String whereto= "";

    @BindView(R.id.work_cb) CheckBox workCB;

    @BindView(R.id.festive_cb)
    CheckBox festiveCB;

    @BindView(R.id.gym_cb)
    CheckBox gymCB;

    @BindView(R.id.casual_cb)
    CheckBox casualCB;

    @BindView(R.id.oa_cb)
    CheckBox oaCB;

    @BindView(R.id.sport_cb)
    CheckBox sportCB;


    ///////////////////////////////////

    @OnClick(R.id.whereto_home)
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

    @OnClick(R.id.nxt_whereto)
    public void toFavorite(){
        if (workCB.isChecked() || festiveCB.isChecked() || gymCB.isChecked() || casualCB.isChecked() || oaCB.isChecked() || sportCB.isChecked()){
            Intent intent = new Intent(this, FavoriteShoeActivity.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder noActivity = new AlertDialog.Builder(this);
            noActivity.setMessage("Please select at least 1 activity type.");
            noActivity.show();
        }

        if (workCB.isChecked()){
            whereto = whereto + " Work";
        }
        if (festiveCB.isChecked()){
            whereto = whereto + " Festive";
        }
        if (gymCB.isChecked()){
            whereto = whereto + " Gym";
        }
        if (casualCB.isChecked()){
            whereto = whereto + " Casual";
        }
        if (oaCB.isChecked()){
            whereto = whereto + " Outdoor Activity";
        }
        if (sportCB.isChecked()){
            whereto = whereto + " Sport";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_add_kicks);
        ButterKnife.bind(this);

    }
}
