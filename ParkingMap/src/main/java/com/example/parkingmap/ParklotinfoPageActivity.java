package com.example.parkingmap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;

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
    private Button route;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parklotinfo_page);
        book = findViewById(R.id.parklotinfo_page_book);
        book.setOnClickListener(v -> {
            InputStream is = null;
            try {
                is = getResources().getAssets().open("parkInfo.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStreamReader isr = null;
            try {
                isr = new InputStreamReader(is,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bfr =new BufferedReader(isr);

            Object[] messages = null;
            try {
                messages = ParkingLotInfo.getRandomLot(bfr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(ParklotinfoPageActivity.this, BookedPageActivity.class);
            String[] floorsName = (String[]) messages[0];
            LotInfo freeLot = (LotInfo) messages[1];
            intent.putExtra("floorsNum", floorsName.length);
            for (int i = 0; i < floorsName.length; i++){
                intent.putExtra("floorName"+i, floorsName[i]);
            }
            intent.putExtra("floor", freeLot.floor);
            intent.putExtra("number", freeLot.number);
            intent.putExtra("x", freeLot.x);
            intent.putExtra("y", freeLot.y);
            startActivity(intent);
        });
    }
}
