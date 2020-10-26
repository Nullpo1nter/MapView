package com.example.parkingmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MarkLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookedPageActivity extends AppCompatActivity {
    private ImageButton goback;
    private Button route;
    private MapView mapView;
    private MarkLayer markLayer;
    private TextView text;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_page);
        Intent intent = getIntent();
        int floorsNum = intent.getIntExtra("floorsNum", 1);
        String[] floorsName = new String[floorsNum];
        linearLayout = findViewById(R.id.booked_page_scrollView_linearLayout);
        String floor = intent.getStringExtra("floor");
        for (int i = 0; i < floorsNum; i++){
            floorsName[i] = intent.getStringExtra("floorName"+i);
            Button bt = new Button(this);
            bt.setText(floorsName[i]);
            bt.setWidth(linearLayout.getWidth());
            if (floorsName[i].equals(floor)){
                System.out.println("************equals");
                bt.setBackgroundColor(Color.parseColor("#6699FF"));
            }else{
                System.out.println("************not equal");
                bt.setBackgroundColor(Color.parseColor("#F5F5F5"));
                bt.setTextColor(Color.parseColor("#000000"));
            }
            linearLayout.addView(bt);
        }
        int lotNum = intent.getIntExtra("number", 0);
        float posX = intent.getFloatExtra("x", 0);
        float posY = intent.getFloatExtra("y", 0);
        goback = findViewById(R.id.booked_page_goback);
        goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        route = findViewById(R.id.booked_page_route);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BookedPageActivity.this, RoutePageActivity.class);
                startActivity(intent);
            }
        });
        mapView = (MapView) findViewById(R.id.mapview);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open("parkinglot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        mapView.setMapViewListener(new MapViewListener() {
            @Override
            public void onMapLoadSuccess() {
                List<PointF> marks = new ArrayList<>();
                marks.add(new PointF(posX, posY));
                List<String> marksName = new ArrayList<>();
                marksName.add(lotNum + "号停车位");
                markLayer = new MarkLayer(mapView, marks, marksName);
                mapView.addLayer(markLayer);
                mapView.refresh();
            }
            @Override
            public void onMapLoadFail() {
            }
        });
        text = findViewById(R.id.booked_page_text);
        text.setText("成功预定"+floor+"层"+lotNum + "号停车位");
    }
}
