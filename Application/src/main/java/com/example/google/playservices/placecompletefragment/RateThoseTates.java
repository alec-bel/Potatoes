package com.example.google.playservices.placecompletefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RateThoseTates extends Activity {

    Button good;
    Button bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_those_tates);

        Intent i = getIntent();
        String mName = i.getStringExtra("name");
        String mLocation = i.getStringExtra("location");
        double mCurrent_rating = i.getDoubleExtra("current_rating", 0);

        ////Alec's not sure if this works
        final TextView rest_name = (TextView) findViewById(R.id.rest_name);
        rest_name.setText(mName);
        final TextView rating = (TextView) findViewById(R.id.rating);
        if (mCurrent_rating > -.5){
            rating.setText(Double.toString(mCurrent_rating));
        }
        else{
            rating.setText("none");
        }

        ////Alec's not sure if this works

        good = (Button) findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent k = new Intent (RateThoseTates.this, MainActivity.class);
                //startActivity(k);
                finish();
            }
        });

        bad = (Button) findViewById(R.id.bad);
        bad.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent k = new Intent (RateThoseTates.this, MainActivity.class);
                //startActivity(k);
                finish();
            }
        });

    }

}
