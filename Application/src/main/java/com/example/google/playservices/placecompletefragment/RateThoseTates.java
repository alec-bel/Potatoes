package com.example.google.playservices.placecompletefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;


public class RateThoseTates extends Activity {
    private String[] places = {"Angelo's", "Fleetwood Diner", "Frank's Restaurant", "Hunter House Hamburgers", "Afternoon Delight" };

    Button good;
    Button bad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_those_tates);

        Intent i = getIntent();
        String mName = i.getStringExtra("name");
        String mLocation = i.getStringExtra("location");
        double mCurrent_rating = -1;
        final int[] totVotes = i.getIntArrayExtra("totVotes");
        final int[] numVotes = i.getIntArrayExtra("numVotes");
        final double[] ratings = i.getDoubleArrayExtra("ratings");
        final int spot = i.getIntExtra("spot", -1);

        if (spot != -1){
            mCurrent_rating = ratings[spot];
        }


        ////Alec's not sure if this works
        final TextView rest_name = (TextView) findViewById(R.id.rest_name);
        rest_name.setText(mName);
        final TextView rating = (TextView) findViewById(R.id.rating);
        if (mCurrent_rating > -.5){
            double text = ceil(mCurrent_rating*100);
            rating.setText(Double.toString(text) + "%");
        }
        else{
            rating.setText("none");
        }

        ////Alec's not sure if this works

        good = (Button) findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            if (spot != -1) {


                numVotes[spot]++;
                totVotes[spot]++;
                ratings[spot] = numVotes[spot]*1.0/totVotes[spot];
            }

                Intent k = new Intent (RateThoseTates.this, MainActivity.class);
                k.putExtra("ratings", ratings);
                k.putExtra("totVotes", totVotes);
                k.putExtra("numVotes", numVotes);
                startActivity(k);
            //finish();
        }

        });

        bad = (Button) findViewById(R.id.bad);
        bad.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
            if(spot != -1) {
                totVotes[spot]++;
                ratings[spot] = numVotes[spot]*1.0/totVotes[spot];
            }
                Intent k = new Intent (RateThoseTates.this, MainActivity.class);
                k.putExtra("ratings", ratings);
                k.putExtra("totVotes", totVotes);
                k.putExtra("numVotes", numVotes);
                startActivity(k);
                //finish();
            }
        });

    }

}
