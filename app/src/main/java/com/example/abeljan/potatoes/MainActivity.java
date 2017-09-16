package com.example.abeljan.potatoes;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button map_btn;
    Button scoreboard_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFrag = MapFragment.newInstance();
        mapFrag.getMapAsync(this);

        scoreboard_btn = (Button) findViewById(R.id.scoreboard_btn);
        scoreboard_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,ViewRanking.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng UoM = new LatLng(42.276938, -83.738205); // Block 'M'
        map.addMarker(new MarkerOptions().position(UoM).title("University of Michigan"));
        map.moveCamera(CameraUpdateFactory.newLatLng(UoM));
    }
}
