package com.example.elliothannahiii.kotd;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyKicksActivity extends BaseActivity {

    ArrayList<String> yourShoeList = new ArrayList<>();
    private KOTDDBHandler kotddbHandler;
    private ArrayAdapter<String> listadapter;
    private String sw;
    private String sa;
    private String sf;
    private int toggle = 0;

    @BindView(R.id.your_kicks_lv) ListView kicksLV;
    @BindView(R.id.noshoesadded) TextView noShoesAdded;

    @OnClick(R.id.delete_all)
    public void deleteMyShoes(){
        if (yourShoeList.size() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No shoes to delete bro.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are You Sure?");
            builder.setMessage("You will lose all of your shoes!");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    kotddbHandler.deleteAllShoes();
                    kicksLV.setVisibility(View.GONE);
                    noShoesAdded.setVisibility(View.VISIBLE);
                    yourShoeList.clear();
                    toastShort("Shoes Deleted.");
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    @OnClick(R.id.my_kicks_home)
    public void backToHome(){
        toActivity(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_kicks);
        ButterKnife.bind(this);
        kotddbHandler = KOTDDBHandler.getInstance(this, null, null, 1);
        displayShoeNames();
        normalClickShoeName();
        longClickShoeName();
    }

    private void displayShoeNames() {
        Cursor shoenames = kotddbHandler.shoeNameToString();
        if (shoenames.getCount() == 0){

        }else{
            while(shoenames.moveToNext()){
                kicksLV.setVisibility(View.VISIBLE);
                noShoesAdded.setVisibility(View.GONE);
                yourShoeList.add(shoenames.getString(shoenames.getColumnIndex("shoeName")));
                listadapter = new ArrayAdapter<>(this, R.layout.my_kicks_lv, yourShoeList);
                kicksLV.setAdapter(listadapter);
            }
        }
    }

    public void normalClickShoeName(){
        kicksLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = kicksLV.getItemAtPosition(position).toString();

                Dialog dialog = new Dialog(MyKicksActivity.this);
                dialog.setContentView(R.layout.custom_dialog);

                TextView tvweather = (TextView) dialog.findViewById(R.id.custom_dialog_weather);
                TextView tvactivity = (TextView) dialog.findViewById(R.id.custom_dialog_activity);
                TextView tvfavorite = (TextView) dialog.findViewById(R.id.custom_dialog_favorite);
                TextView tvname = (TextView) dialog.findViewById(R.id.custom_dialog_title);
                tvname.setText(name);

                toastShort(name);

                Cursor shoeData = kotddbHandler.shoeDataToString(name);
                if(shoeData.getCount() == 0){
                    toastShort("No Data");
                }else{
                    while (shoeData.moveToNext()){
                        sw = shoeData.getString(shoeData.getColumnIndex("shoeWeather"));
                        tvweather.setText(sw);
                        sa = shoeData.getString(shoeData.getColumnIndex("shoeActivity"));
                        tvactivity.setText(sa);
                        sf = shoeData.getString(shoeData.getColumnIndex("shoeFavorite"));
                        tvfavorite.setText(sf);
                    }
                }
                ///////////////////////////////////////////////////////////////////////////////
                dialog.show();
                shoeData.moveToNext();
            }
        });
    }

    public void longClickShoeName(){
        kicksLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String name = kicksLV.getItemAtPosition(position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(MyKicksActivity.this);
                builder.setTitle("Delete Shoe");
                builder.setMessage("Are You Sure You Want To Delete \"" + name + "\"?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kotddbHandler.deleteShoe(name);
                        listadapter.remove(name);
                        yourShoeList.remove(name);
                        toastShort(name + " deleted!");
                        if (yourShoeList.size() == 0){
                            kicksLV.setVisibility(View.GONE);
                            noShoesAdded.setVisibility(View.VISIBLE);
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

}
