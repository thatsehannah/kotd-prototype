package com.example.elliothannahiii.kotd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoePictureActivity extends BaseActivity {

    @OnClick(R.id.home_shoe_pic)
    public void backToHome(){
        toActivity(MainActivity.class);
    }

    @OnClick(R.id.nxt_shoe_pic)
    public void toReview(){
        toActivity(ReviewNewShoeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_picture);
        ButterKnife.bind(this);
    }
}
