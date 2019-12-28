package com.example.heremaps;
//
//<<<<<<< HEAD
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
//||||||| f1b04a4
////import androidx.annotation.Nullable;
////import androidx.appcompat.app.AppCompatActivity;
//
//=======
import android.content.Context;
//>>>>>>> 0f10f2b2202a819080bef1443680524e496e30df
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

//<<<<<<< HEAD
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
//||||||| f1b04a4
//=======
import com.here.sdk.core.Anchor2D;
//>>>>>>> 0f10f2b2202a819080bef1443680524e496e30df

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
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        checkGPSPermission();
//        LocationService obj=new LocationService();
//        obj.getCoordinates();
          loadMapScene();
        startService(new Intent(this,GetGPSCoordinates.class));

//<<<<<<< HEAD
    }

    boolean checkGPSPermission() {
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


        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;


//||||||| f1b04a4

    }


    private void loadMapScene() {

        GeoCoordinates geoCoordinates = new GeoCoordinates(19.384046299999998, 72.8284918);

        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(MapScene.ErrorCode errorCode) {
                if (errorCode == null) {
                    //Current location view
                    mapView.getCamera().setTarget(geoCoordinates);
                    mapView.getCamera().setZoomLevel(14);
                    //Show the marker on map
                    MapImage mapImage = MapImageFactory.fromResource(context.getResources(),R.drawable.pin);
                    MapMarker mapMarker = new MapMarker(geoCoordinates);

                    MapMarkerImageStyle mapMarkerImageStyle = new MapMarkerImageStyle();
                    mapMarkerImageStyle.setAnchorPoint(new Anchor2D(0.5F, 1));
                    mapMarkerImageStyle.setScale(0.06F);
                    mapMarker.addImage(mapImage, mapMarkerImageStyle);
                    mapView.getMapScene().addMapMarker(mapMarker);

                } else {
                    Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
                }
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
//            case 101:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    deviceId();
//                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
//                    closeNow();
//                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
//                }
//                break;
                case 102:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("GPS In MainActivity","GPS Permissions granted");
                      startService(new Intent(this,GetGPSCoordinates.class));
//                    GpsPermission = true;

                } else {
//                    GpsPermission = false;
                    Toast.makeText(getApplicationContext(),"Location Permission Denied ",Toast.LENGTH_LONG).show();
//                    closeNow();
                    //Permission Required Prompt
//                    checkGPSPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

//        @Override
//        protected void onPause() {
//            super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }


}
}
