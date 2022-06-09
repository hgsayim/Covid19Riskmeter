package com.covid19riskmeter;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.covid19riskmeter.Classes.Database;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.covid19riskmeter.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int[] colors = {Color.rgb(102,225,0),Color.rgb(255,0,0)};
        float[] startPoints = {0.2f,1f};
        Gradient gradient = new Gradient(colors,startPoints);
        // Add a marker in Sydney and move the camera
        List<LatLng> list = new ArrayList<LatLng>();
        LatLng our_location = new LatLng(41.066175, 29.015167);
        for(LatLng l: Database.locations){
            list.add(l);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(our_location));
        CameraPosition position = new CameraPosition.Builder().target(our_location).zoom(14).bearing(0).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder().data(list).gradient(gradient).build();
        mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));


    }
}