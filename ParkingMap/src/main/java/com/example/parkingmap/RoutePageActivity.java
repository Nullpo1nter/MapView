package com.example.parkingmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;


public class RoutePageActivity extends AppCompatActivity {
    private ImageButton goback;
    private Button navigate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_page);
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
