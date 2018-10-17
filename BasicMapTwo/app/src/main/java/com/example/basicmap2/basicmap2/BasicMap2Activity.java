package com.example.basicmap2.basicmap2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class BasicMap2Activity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int DEFAULT_ZOOM = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_map2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void mOnClick(View v) {
        if (mMap == null) return;
        switch (v.getId()) {
            case R.id.btnGotoSeoul:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(37.548974, 126.994013), DEFAULT_ZOOM)
                );
                break;
            case R.id.btnGotoNewYork:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(40.6976701, -74.259861), DEFAULT_ZOOM));
        }
    }
}
