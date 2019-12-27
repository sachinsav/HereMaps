package com.example.heremaps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapScene;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HereMaps";
    private MapViewLite mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        loadMapScene();


    }

    private void loadMapScene() {

        GeoCoordinates geoCoordinates = new GeoCoordinates(19.384046299999998, 72.8284918);

        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapScene.ErrorCode errorCode) {
                if (errorCode == null) {
                    //Current location view
                    mapView.getCamera().setTarget(geoCoordinates);
                    mapView.getCamera().setZoomLevel(14);
                    //Show the marker on map
                    MapMarker mapMarker = new MapMarker(geoCoordinates);
                    mapView.getMapScene().addMapMarker(mapMarker);
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


}
