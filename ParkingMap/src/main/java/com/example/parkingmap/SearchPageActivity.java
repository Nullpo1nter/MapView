package com.example.parkingmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchPageActivity extends AppCompatActivity {

    private static final String TAG = "ParkingSearchActivity";

    private TextView textView0;
    private ImageButton goback;
    private ScrollView scrollView;
    private ImageButton scrollhide;
    private LinearLayout info;

    private double posX;
    private double posY;

    private ArrayList<String> lotName = new ArrayList<>();
    private ArrayList<Double> lotX = new ArrayList<>();
    private ArrayList<Double> lotY = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hideNavigationBar(this);
        Intent intent = getIntent();
        posX = intent.getDoubleExtra("X", 0);
        posY = intent.getDoubleExtra("Y", 0);
        Log.d(TAG, "onCreate: " + posX + " " + posY);
        lotName.add("停车场1号");
        lotName.add("停车场2号");
        lotX.add(114.0);
        lotX.add(-122.0);
        lotY.add(22.6);
        lotY.add(37.4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        scrollView = findViewById(R.id.search_page_scrollview);
        scrollhide = findViewById(R.id.search_page_scrollhide);
        goback = findViewById(R.id.search_page_goback);
        scrollhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.GONE);//隐藏不参与布局（不占地方）
                DisplayMetrics dm = getResources().getDisplayMetrics();
                float height = dm.heightPixels;
                scrollhide.setY(height-scrollhide.getHeight());
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
            }
        });

        Log.d(TAG, String.valueOf(lotName.size()));

        LinearLayout layout = findViewById(R.id.park_info);
        for (int i = 0; i < lotName.size(); i++) {
            if(lotX.get(i) - posX <= 0.1 && lotY.get(i) - posY <= 0.1){
                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90));
                textView.setBackgroundResource(R.drawable.textview_border);
                textView.setTextSize(20);
                textView.setTextColor(R.color.colorBlack);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setText(lotName.get(i));
                textView.setClickable(true);
                int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("name", lotName.get(finalI));
                        intent.putExtra("X", lotX.get(finalI));
                        intent.putExtra("Y", lotY.get(finalI));
                        setResult(1, intent);
                        finish();
                    }
                });
                layout.addView(textView);
            }
        }

    }
}
