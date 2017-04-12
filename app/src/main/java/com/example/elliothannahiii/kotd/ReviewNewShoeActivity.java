package com.example.elliothannahiii.kotd;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewNewShoeActivity extends BaseActivity {

    @BindView(R.id.rv_shoe_name) TextView rvName;
    @BindView(R.id.rv_activity) TextView rvActivity;
    @BindView(R.id.rv_favorite) TextView rvFavorite;
    @BindView(R.id.rv_weather) TextView rvWeather;

    private String shoeName = ShoeNameActivity.shoeName;
    private String whereTo = WhereToAddKicksActivity.whereto;
    private String weather = WeatherAddKicksActivity.weathernames;
    private String favValue = FavoriteShoeActivity.fav_value;
    private KOTDDBHandler kotddbHandler;

    @OnClick(R.id.submit_shoe)
    public void submitShoeToDB (){
        kotddbHandler = KOTDDBHandler.getInstance(this, null, null, 1);
        Shoes shoe = new Shoes(shoeName, weather, whereTo, favValue);
        kotddbHandler.addShoe(shoe);
        toastShort("Shoe Successfully Added!");
        toActivity(MainActivity.class);
    }


    @OnClick(R.id.review_home)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_new_shoe);
        ButterKnife.bind(this);
        rvName.setText(shoeName);
        rvActivity.setText(whereTo);
        rvWeather.setText(weather);
        rvFavorite.setText(favValue);

    }

}
