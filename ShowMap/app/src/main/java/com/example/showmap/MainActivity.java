package com.example.showmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button nextpage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        nextpage = findViewById(R.id.button);
        nextpage.setBackgroundColor(Color.parseColor("#ffffff"));
        nextpage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,SendSms.class);
            startActivity(intent);
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng india = new LatLng(30.269022349338005, 77.99311474640321);  // Latitude and Longitude of India
        mMap.addMarker(new MarkerOptions().position(india).title("uttarakhand"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 10));  // Zoom level 5 for country-wide view
    }
}