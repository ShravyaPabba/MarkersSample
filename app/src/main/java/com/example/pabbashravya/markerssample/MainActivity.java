package com.example.pabbashravya.markerssample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView recyclerView;
    private LocationsAdapter adapter;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 33;

    private List<Datum> locationsList=FakeDataUtils.getFakeData();
    //private List<Datum> locationsList=NetworkUtils.getLocationsFromNetwork();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions();
        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        adapter=new LocationsAdapter(locationsList,this);
        recyclerView.setAdapter(adapter);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        if (shouldProvideRationale) {
            Snackbar.make(
                    findViewById(R.id.activity_main),
                    "App needs access to location",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        } else {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map));
                mapFragment.getMapAsync(this);

            } else {

                Snackbar.make(
                        findViewById(R.id.activity_main),
                        "App needs this permission.Please change it in the App Settings",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Datum centerLoc = locationsList.get(4);
        LatLng ll = new LatLng(Double.parseDouble(centerLoc.getLatitude()), Double.parseDouble(centerLoc.getLongitude()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 17));
        for (Datum loc : locationsList) {
            LatLng latLng = new LatLng(Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude()));
            googleMap.addMarker(new MarkerOptions().position(latLng).title(loc.getTitle()).icon(BitmapDescriptorFactory.fromBitmap(getBitmap())));
        }

    }

    private Bitmap getBitmap() {
        return BitmapFactory.decodeResource(getResources(),R.drawable.placeholder);
    }
}
