package com.example.elliothannahiii.kotd;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoeNameActivity extends BaseActivity {

    public static String shoeName;
    
    @BindView(R.id.et_shoe_name) EditText editText;

    ///////////////////////////////////////

    @OnClick(R.id.sn_home)
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

    @OnClick(R.id.nxt_shoe_name)
    public void toWeatherType(){
        shoeName = editText.getText().toString();
        if (shoeName.isEmpty()){
            AlertDialog.Builder noShoeName = new AlertDialog.Builder(this);
            noShoeName.setMessage("Please enter a shoe name.");
            noShoeName.show();
        }else if (shoeName.contains("\'") || shoeName.contains("\"")){
            AlertDialog.Builder removeSymbol = new AlertDialog.Builder(this);
            removeSymbol.setMessage("Please remove symbol(s).");
            removeSymbol.show();
        }else{
            toActivity(WeatherAddKicksActivity.class);
        }

    }

//    public void addShoeName(String shoeName){
//        boolean insertName
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_name_kicks);
        ButterKnife.bind(this);

    }


}
