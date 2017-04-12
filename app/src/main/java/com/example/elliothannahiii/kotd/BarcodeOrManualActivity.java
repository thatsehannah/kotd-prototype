package com.example.elliothannahiii.kotd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarcodeOrManualActivity extends BaseActivity {

    @OnClick(R.id.manualButton)
    public void goToShoeName(){
        toActivity(ShoeNameActivity.class);
    }

    @OnClick(R.id.barcodeButton)
    public void goToBarcode(){
        toActivity(BarcodeActivity.class);
    }

    @OnClick(R.id.newShoeHome)
    public void goHome(){
        toActivity(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_or_manual);
        ButterKnife.bind(this);
    }
}
