package com.example.google.playservices.placecompletefragment;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends SampleActivityBase implements PlaceSelectionListener, OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private TextView mPlaceDetailsText;

    private TextView mPlaceAttribution;

    private GoogleMap gMap;

    private int[] totVotes;
    private int[] numVotes;
    private double[] ratings;

    private String[] places = {"Angelo's", "Fleetwood Diner", "Frank's Restaurant", "Hunter House Hamburgers", "Afternoon Delight" };

    private ArrayList<Thing> things;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Retrieve the PlaceAutocompleteFragment.
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);

        // Retrieve the TextViews that will display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);

        // Set up map fragment
        MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.gmap);
        mapFrag.getMapAsync(this);
        totVotes = new int[]{204, 198, 100, 199, 196};
        numVotes = new int[]{26, 159, 75, 111, 185};
        ratings = new double[]{.13, .8, .75, .56, .94};

    }

    void debug_show_place(Place place) {
        Log.i(TAG, "Place Selected: " + place.getName());

        // Format the returned place's details and display them in the TextView.
        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

        CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            //mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
            mPlaceAttribution.setText(attributions.toString());
        } else {
            mPlaceAttribution.setText("");
        }

    }

    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    @Override
    public void onPlaceSelected(Place place) {
        LatLng loca = place.getLatLng();
        CharSequence name = place.getName();
        gMap.addMarker(new MarkerOptions().position(loca).title(name.toString()));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15f));

        debug_show_place(place);
    }

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    @Override
    public void onError(Status status) {
        Log.e(TAG, "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to format information about a place nicely.
     */
    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
            CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }

    @Override
    public void onMapReady(GoogleMap map) {
        gMap = map;
        gMap.setOnMarkerClickListener(this);

        LatLng UoM = new LatLng(42.276938, -83.738205); // Block 'M'
        map.addMarker(new MarkerOptions().position(UoM).title("University of Michigan"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(UoM, 15f));
    }

    double db_current_rating(String id) {
        return 0;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        final Intent intent = new Intent(this, RateThoseTates.class);
        intent.putExtra("name", marker.getTitle());
        intent.putExtra("location", marker.getPosition().toString());
        intent.putExtra("current_rating", db_current_rating(marker.getId()));
        boolean found = false;
        for (int k = 0; k < 5; ++k){
            if (marker.getTitle().equals(places[k])){
                intent.putExtra("spot", k);
                found = true;
            }
        }
        if (!found){
            intent.putExtra("spot", -1);
        }
        intent.putExtra("ratings", ratings);
        intent.putExtra("totVotes", totVotes);
        intent.putExtra("numVotes", numVotes);

        startActivity(intent);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (data.hasExtra("ratings")){
                ratings = data.getExtras().getDoubleArray("ratings");
            }
            if (data.hasExtra("numVotes")){
                numVotes = data.getExtras().getIntArray("numVotes");
            }
            if (data.hasExtra("totVotes")){
                totVotes = data.getExtras().getIntArray("totVotes");
            }
        }

    }
}
