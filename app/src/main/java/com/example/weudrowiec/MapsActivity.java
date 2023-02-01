package com.example.weudrowiec;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.weudrowiec.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private double latitude;
    private double longitude;
    Button pogoda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pogoda=findViewById(R.id.pogoda);
        latitude=getIntent().getDoubleExtra("latitude",0.0);
        longitude=getIntent().getDoubleExtra("longitude",0.0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        pogoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this,HomeActivity2.class));
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Muzeum = new LatLng(53.13333, 23.16433);
        LatLng Park = new LatLng(53.13000, 23.16400);
        LatLng Cerkwia = new LatLng(53.13579, 23.16488);
        LatLng Politechnika = new LatLng(53.13722, 23.16370);
        mMap.addMarker(new MarkerOptions().position(Muzeum).title("Muzeum"));
        mMap.addMarker(new MarkerOptions().position(Park).title("Park"));
        mMap.addMarker(new MarkerOptions().position(Cerkwia).title("Cerkwia"));
        mMap.addMarker(new MarkerOptions().position(Politechnika).title("Politechnika"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Muzeum,13));
        mMap.getMaxZoomLevel();
    }
}