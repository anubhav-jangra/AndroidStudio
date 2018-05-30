package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evol on 29/3/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private List<Earthquake> earthquakes = new ArrayList<Earthquake>();
    private LayoutInflater inflater;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakeArrayList){
        super(context, 0, earthquakeArrayList);
        this.earthquakes = earthquakeArrayList;
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = inflater.inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        final Earthquake currentEarthquakeItem = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formatMagnitude(currentEarthquakeItem.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquakeItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        String place = currentEarthquakeItem.getPlace();
        String offset;
        String location;
        int i;
        if(place.contains("km ") && place.contains("of "))
        {
            i = place.indexOf("of ");
            offset = place.substring(0, i + 2);
            location = place.substring(i+3, place.length());
        }
        else
        {
            offset = "Near the";
            location = place;
        }

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place);

        offsetTextView.setText(offset);
        placeTextView.setText(location);

        Date dateObject = new Date(currentEarthquakeItem.getTimeInMilliseconds());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        dateTextView.setText(formatDate(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        timeTextView.setText(formatTime(dateObject));

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentEarthquakeItem.getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(url));
                if (browserIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(browserIntent);
                }
            }
        });

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    public void addEarthquakes(List<Earthquake> data){
        earthquakes.addAll(data);
        notifyDataSetChanged();
    }
}