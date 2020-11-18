package com.example.parkingmap;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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
    private ScrollView scrollView;
    private ImageButton scrollhide;
    private LinearLayout info;

    private double posX;
    private double posY;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                    posX = amapLocation.getLongitude();
                    posY = amapLocation.getLatitude();
                    Log.d("",amapLocation.getLongitude() + " " + amapLocation.getLatitude());
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    ArrayList<String> parkingLotName = new ArrayList<>();
    ArrayList<Double> parkingLotX = new ArrayList<>();
    ArrayList<Double> parkingLotY = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hideNavigationBar(this);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(1000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

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
//        textView0 = findViewById(R.id.search_page_parkslot_info);
//        textView0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(SearchPageActivity.this, ParklotinfoPageActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
