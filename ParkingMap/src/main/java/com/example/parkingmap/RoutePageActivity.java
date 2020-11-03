package com.example.parkingmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MarkLayer;
import com.onlylemi.mapview.library.layer.RouteLayer;
import com.onlylemi.mapview.library.utils.MapUtils;

import java.io.IOException;
import java.util.List;


public class RoutePageActivity extends AppCompatActivity {
    private ImageButton goback;
    private Button navigate;
    private MapView mapView;
    private RouteLayer routeLayer;
    private MarkLayer markLayer;
    private EditText start;
    private EditText end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_page);
        Intent intent = getIntent();
        float targetX = intent.getFloatExtra("targetX", 0);
        float targetY = intent.getFloatExtra("targetY", 0);
        String targetPlace = intent.getStringExtra("targetPlace");
        start = findViewById(R.id.route_page_editText_start);
        end = findViewById(R.id.route_page_editText_end);
        start.setText("我的位置");
        end.setText(targetPlace);
        mapView = findViewById(R.id.mapview);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open("parkinglot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        TestData.init();
        MapUtils.init(TestData.nodeList.size(), TestData.nodesContactList.size());
        mapView.setMapViewListener(new MapViewListener() {
            @Override
            public void onMapLoadSuccess() {
                routeLayer = new RouteLayer(mapView);
//                mapView.addLayer(routeLayer);

//                markLayer = new MarkLayer(mapView, TestData.marks, TestData.marksName);
//                mapView.addLayer(markLayer);
//                markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
//                    @Override
//                    public void markIsClick(int num) {
//                        PointF target = new PointF(TestData.marks.get(num).x, TestData.marks.get(num).y);
//                        List<Integer> routeList = MapUtils.getShortestDistanceBetweenTwoPoints
//                                (TestData.marks.get(39), target, TestData.nodeList, TestData.nodesContactList);
//                        routeLayer.setNodeList(TestData.nodeList);
//                        routeLayer.setRouteList(routeList);
//                        mapView.refresh();
//                    }
//                });
//                mapView.refresh();
//                System.out.println("***********************"+TestData.marks.size()+"*************");
                PointF target = new PointF(targetX,targetY);
//                System.out.println("***********************"+target.x+"*************");
                List<Integer> routeList = MapUtils.getShortestDistanceBetweenTwoPoints
                        (TestData.nodeList.get(66), target, TestData.nodeList, TestData.nodesContactList);
                routeLayer.setNodeList(TestData.nodeList);
                routeLayer.setRouteList(routeList);
                mapView.addLayer(routeLayer);
                mapView.refresh();
            }

            @Override
            public void onMapLoadFail() {
            }

        });
        goback = findViewById(R.id.route_page_goback);
        goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navigate = findViewById(R.id.booked_page_navigate);
        navigate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RoutePageActivity.this, NavigatePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
