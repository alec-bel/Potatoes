package com.example.google.playservices.placecompletefragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ivan on 9/17/17.
 */

public class Thing extends Object {
    private String s_;
    private int total_votes = 0;
    private int updoots = 0;

    public Thing(String m) {
        this.s_ = m;
    }

    public Thing(String s, int total_votes, int updoots) {
        this.s_ = s;
        this.total_votes = total_votes;
        this.updoots = updoots;
    }

    public String getName() {
        return s_;
    }

    public int getTotal_votes() {return total_votes;}

    public void setTotal_votes(int i) {this.total_votes = i;}

    public int getUpdoots() {return updoots;}

    public void setUpdoots(int i) {this.total_votes = i;}
}
