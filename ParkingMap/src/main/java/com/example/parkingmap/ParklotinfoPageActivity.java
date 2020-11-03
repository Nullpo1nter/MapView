package com.example.parkingmap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.onlylemi.mapview.library.MapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ParklotinfoPageActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSearchActivity";

    private MapView mapView;
    private Button book;
    private TextView textView;
    private Button route;
    private ImageView goback;
    private BufferedReader bfr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parklotinfo_page);
        try {
            InputStream is = getResources().getAssets().open("parkInfo.txt");
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            bfr =new BufferedReader(isr);
            ParkingLotInfo.init(bfr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView = findViewById(R.id.parklotinfo_page_textView);
        textView.setText(ParkingLotInfo.name+"\n"+ParkingLotInfo.location+"\n剩余车位"+ParkingLotInfo.remain);
        goback = findViewById(R.id.parklotinfo_page_goback);
        goback.setOnClickListener(v -> finish());
        book = findViewById(R.id.parklotinfo_page_book);
        book.setOnClickListener(v -> {
            LotInfo freeLot = ParkingLotInfo.getRandomLot();
            Intent intent = new Intent(ParklotinfoPageActivity.this, BookedPageActivity.class);
            intent.putExtra("parklotName", ParkingLotInfo.name);
            intent.putExtra("floorsNum", ParkingLotInfo.floors);
            for (int i = 0; i < ParkingLotInfo.floors; i++){
                intent.putExtra("floorName"+i, ParkingLotInfo.floorsName[i]);
            }
            intent.putExtra("floor", freeLot.floor);
            intent.putExtra("number", freeLot.number);
            intent.putExtra("x", freeLot.x);
            intent.putExtra("y", freeLot.y);
            startActivity(intent);
        });
    }
}
