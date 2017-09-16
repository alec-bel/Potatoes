package com.example.abeljan.potatoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
//import com.google.android.gms.location.places.PlaceDetectionClient;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends AppCompatActivity {

    Button map_btn;
    Button scoreboard_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map_btn = (Button) findViewById(R.id.map_btn);
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        scoreboard_btn = (Button) findViewById(R.id.scoreboard_btn);
        scoreboard_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,ViewRanking.class);
                startActivity(i);
            }
        });
    }
}
