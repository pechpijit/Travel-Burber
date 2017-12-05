package com.ssru.travel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssru.travel.adapter.AdapterMapTemple;
import com.ssru.travel.model.ModelTravelMap;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MapWatActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    //    private static LatLng b9 = new LatLng(13.7154937, 100.5820363);
    private Marker mPerth;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map3);

        mContext = this;
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        final GoogleMap mMap = map;
        new ConnectAPI().getTravelMap(MapWatActivity.this, mMap);
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {

        Integer clickCount = (Integer) marker.getTag();

        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(MapWatActivity.this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void addMarker(GoogleMap mMap, String string, String url) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelTravelMap>>() {
        }.getType();
        Collection<ModelTravelMap> enums = gson.fromJson(string, collectionType);
        ArrayList<ModelTravelMap> post = new ArrayList<ModelTravelMap>(enums);

        LatLng Position = null;
        int lastIndex = 0;
        for (ModelTravelMap contentModel :
                post) {
            mPerth = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(contentModel.getLa()), Double.parseDouble(contentModel.getLo())))
                    .icon((BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(url + "/travelImage_resize/" + contentModel.getImage()))))
                    .title(contentModel.getName()));
            mPerth.setTag(contentModel.getId());
            if (lastIndex == 0) {
                Position = new LatLng(Double.parseDouble(contentModel.getLa()), Double.parseDouble(contentModel.getLo()));
                lastIndex++;
            }
        }

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
        mMap.setBuildingsEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(Position).zoom(11).build(); // เอาไว้ Fix Location
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));//แบบมี Animation

//        googleMap.getUiSettings().setScrollGesturesEnabled(false);
//        ปิดไม่ให้เลื่อน map
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);// แบบจริง
//        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN);//แบบภาพวาด
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
//        mMap.setInfoWindowAdapter(new MapInfoWindowAdapter(this, markerSet));
    }

    private Bitmap getMarkerBitmapFromView(final String url) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.avatar_marker_image_view);

//        try {
//            URL UR = new URL(url);
//            Bitmap bmp = BitmapFactory.decodeStream(UR.openConnection().getInputStream());
//            markerImageView.setImageBitmap(bmp);
//        } catch (Exception e) {
//            Log.d("loadImage", e+"");
//        }
        Picasso.with(mContext).load(url).error(R.drawable.nopic).into(markerImageView);
//        Picasso.with(mContext).load(url).error(R.drawable.nopic).into(markerImageView);
        Glide.with(mContext).load(url).error(R.drawable.nopic).into(markerImageView);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }


    private Bitmap loadImageFromNetwork(String string) {
        try {
            URL UR = new URL(string);
            return BitmapFactory.decodeStream(UR.openConnection().getInputStream());
        } catch (Exception e) {
            return null;
        }
    }

    public void setAdap(String data, String url) {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ModelTravelMap>>() {
        }.getType();
        Collection<ModelTravelMap> enums = gson.fromJson(data, collectionType);
        final ArrayList<ModelTravelMap> posts = new ArrayList<ModelTravelMap>(enums);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterMapTemple adapter = new AdapterMapTemple(mContext, posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterMapTemple.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID = posts.get(position).getId();
//                String la = posts.get(position).getTempleLatittude();
//                String lo = posts.get(position).getTempleLongitude();
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?daddr="+la+","+lo));
//                startActivity(intent);
                startActivity(new Intent(mContext, TravelDetailActivity.class).putExtra("id", ID));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
