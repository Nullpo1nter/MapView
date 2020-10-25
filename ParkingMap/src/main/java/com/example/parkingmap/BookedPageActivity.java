package com.example.parkingmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;

public class BookedPageActivity extends AppCompatActivity {
    private ImageButton goback;
    private Button route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_page);
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
    }
}
