package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nick on 1/11/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {

        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Earthquake earthquake = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = convertView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(earthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = earthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];

        } else {

            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;

        }

        TextView offset = convertView.findViewById(R.id.location_offset);
        offset.setText(locationOffset);

        TextView location = convertView.findViewById(R.id.primary_location);
        location.setText(primaryLocation);

        TextView date = convertView.findViewById(R.id.date);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        date.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = convertView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return convertView;

    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeLevel = (int) magnitude;

        switch (magnitudeLevel) {

            case 0:
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);

            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);

            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);

            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);

            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);

            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);

            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);

            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);

            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);

            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);

        }

    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);

    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return timeFormat.format(dateObject);

    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }

}
