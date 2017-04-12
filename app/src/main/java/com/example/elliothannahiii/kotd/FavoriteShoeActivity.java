package com.example.elliothannahiii.kotd;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteShoeActivity extends BaseActivity {

    public static String fav_value;

    @OnClick(R.id.fav_yes_button)
    public void yesFav(){
        fav_value=" YES";
        toActivity(ShoePictureActivity.class);
    }

    @OnClick(R.id.fav_no_button)
    public void noFav() {
        fav_value=" NO";
        toActivity(ShoePictureActivity.class);
    }

    @OnClick(R.id.fav_home)
    public void backToHome() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_shoe);
        ButterKnife.bind(this);
    }
}
