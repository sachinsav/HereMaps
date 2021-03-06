package com.example.heremaps;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.CompoundButton;
import android.widget.Switch;


import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapImage;
import com.here.sdk.mapviewlite.MapImageFactory;
import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapMarkerImageStyle;
import com.here.sdk.mapviewlite.MapScene;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HereMaps";
    private MapViewLite mapView;
    private MapStyle mapStyle;
    private Switch aSwitch;
    Context context = this;
    String zone,sub_zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        aSwitch = findViewById(R.id.switchView);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        checkGPSPermission();
//        LocationService obj=new LocationService();
//        obj.getCoordinates();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mapStyle = MapStyle.SATELLITE;
                    loadMapScene(mapStyle);
                }
                else{
                    mapStyle = MapStyle.NORMAL_DAY;
                    loadMapScene(mapStyle);
                }
            }
        });

        startService(new Intent(this,GetGPSCoordinates.class));

//<<<<<<< HEA

    }

    void checkGPSPermission() {
        Log.d("MainActivity", "Inside CheckGPSPermission");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {   //permissions not granted
            final int d = Log.d("GPS Access in Main", "Requesting GPS Location");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
        } else {
            //permissions granted
            //ContextCompat.startForegroundService(this,new Intent(this,GetGPSCoordinates.class));
//            GpsPermission = true;
        }
    }


    /**Loads MapScene in NaturalDay, Satellite View and locates the user by using pins on map **/
    private void loadMapScene(MapStyle mapStyle) {

        GeoCoordinates geoCoordinatesAlert = new GeoCoordinates(19.384046299999998, 72.8284918);
        GeoCoordinates geoCoordinatesSaviour = new GeoCoordinates(19.404046299999998, 72.8284918);

        // Load a scene from the SDK to render the map with a map style.

        mapView.getMapScene().loadScene(this.mapStyle, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(MapScene.ErrorCode errorCode) {
                if (errorCode == null) {
                    //Current location view
                    mapView.getCamera().setTarget(geoCoordinatesAlert);
                    mapView.getCamera().setZoomLevel(14);
                    //Show the marker on map
                    MapImage mapImageAlert = MapImageFactory.fromResource(context.getResources(),R.drawable.alert);
                    MapMarker mapMarkerAlert = new MapMarker(geoCoordinatesAlert);

                    MapImage mapImageSaviour = MapImageFactory.fromResource(context.getResources(),R.drawable.saviour);
                    MapMarker mapMarkerSaviour = new MapMarker(geoCoordinatesSaviour);

                    MapMarkerImageStyle mapMarkerImageStyle = new MapMarkerImageStyle();
                    mapMarkerImageStyle.setAnchorPoint(new Anchor2D(0.5F, 1));
                    mapMarkerImageStyle.setScale(0.06F);
                    mapMarkerAlert.addImage(mapImageAlert, mapMarkerImageStyle);
                    mapView.getMapScene().addMapMarker(mapMarkerAlert);

                    mapMarkerSaviour.addImage(mapImageSaviour, mapMarkerImageStyle);
                    mapView.getMapScene().addMapMarker(mapMarkerSaviour);

                } else {
                    Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    public void onClick(View view) {

        GetGPSCoordinates gps=new GetGPSCoordinates();
        zone=gps.zone;
        sub_zone=gps.sub_zone;
//        Log.d("onAlertClick1", zone+" and "+sub_zone);
        new Firebase_Helper2().retrieve(zone, sub_zone);
    }



}
