package com.example.android.quakereport;

/**
 * Created by evol on 29/3/18.
 */

public class Earthquake {

    private double mMag;
    private String mPlace;
    private long mTimeInMilliseconds;
    private String mUrl;

//    public Earthquake(Context context){
//        this.context = context;
//    }

    public Earthquake(double mag, String place, long timeInMilliSeconds, String url){
        mMag = mag;
        mPlace = place;
        mTimeInMilliseconds = timeInMilliSeconds;
        mUrl = url;
    }

    public double getMagnitude(){
        return mMag;
    }

    public String getPlace(){
        return mPlace;
    }

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
