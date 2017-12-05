package com.ssru.travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.Hashtable;

public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final Hashtable<String, Boolean> markerSet;
    private Context context;
    private View myContentsView;

    public MapInfoWindowAdapter(Context context, Hashtable<String, Boolean> markerSet) {
        this.context = context;
        this.markerSet = markerSet;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myContentsView = inflater.inflate(R.layout.view_marker, null);
        ImageView imageView = (ImageView) myContentsView.findViewById(R.id.avatar_marker_image_view);
        boolean isImageLoaded = markerSet.get(marker.getId());
        if (isImageLoaded) {
            Picasso.with(context)
                    .load(marker.getSnippet())
                    .placeholder(R.drawable.nopic)
                    .into(imageView);
        } else {
            isImageLoaded = true;
            markerSet.put(marker.getId(), isImageLoaded);
            Picasso.with(context)
                    .load(marker.getSnippet())
                    .placeholder(R.drawable.nopic)
                    .into(imageView, new InfoWindowRefresher(marker));
        }

        return myContentsView;
    }
}
