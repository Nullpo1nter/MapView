package com.example.parkingmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;

import java.io.IOException;
import java.util.Random;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;

public class SearchPageActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSearchActivity";

    private MapView mapView;
    private TextView textView0;
    private ScrollView scrollView;
    private ImageButton scrollhide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hideNavigationBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        scrollView = findViewById(R.id.search_page_scrollview);
        scrollhide = findViewById(R.id.search_page_scrollhide);
        scrollhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.GONE);//隐藏不参与布局（不占地方）
                DisplayMetrics dm = getResources().getDisplayMetrics();
                float height = dm.heightPixels;
                scrollhide.setY(height-scrollhide.getHeight());
            }
        });
        textView0 = findViewById(R.id.search_page_parkslot_info);
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchPageActivity.this, ParklotinfoPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
