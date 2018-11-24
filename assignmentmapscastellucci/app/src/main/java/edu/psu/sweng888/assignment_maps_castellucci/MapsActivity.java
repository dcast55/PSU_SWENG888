package edu.psu.sweng888.assignment_maps_castellucci;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.psu.sweng888.assignment_maps_castellucci.broadcast.BroadcastReceiverMap;
import edu.psu.sweng888.assignment_maps_castellucci.model.MapLocation;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String LOG_MAP = "GOOGLE_MAPS";

    private GoogleMap mMap;
    private LatLng currentLatLng;
    private Marker currentMapMarker;
    private Button mLoadButton;

    private IntentFilter intentFilter = null;
    private BroadcastReceiverMap broadcastReceiverMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLoadButton = findViewById(R.id.btn_load_firebase);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMarkersFromFirebase();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intentFilter = new IntentFilter("edu.psu.sweng888.assignment_maps_castellucci.MAP_LOCATION_BROADCAST");
        broadcastReceiverMap = new BroadcastReceiverMap();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register the Broadcast Receiver.
        registerReceiver(broadcastReceiverMap, intentFilter);
    }

    @Override
    protected void onStop() {
        // Unregister the Broadcast Receiver
        unregisterReceiver(broadcastReceiverMap);
        super.onStop();
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

        mMap.getUiSettings().setZoomControlsEnabled(true);

        Intent intent = getIntent();
        Double latitude = intent.getDoubleExtra("LATITUDE", Double.NaN);
        Double longitude = intent.getDoubleExtra("LONGITUDE", Double.NaN);
        String location = intent.getStringExtra("LOCATION");

        currentLatLng = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions()
                .position(currentLatLng)
                .title(location)
        );

        mapCameraConfiguration();
        useMapClickListener();
        useMarkerClickListener();
        useMapLongClickListener();
        useCameraMoveListener();
    }

    private void mapCameraConfiguration() {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLatLng)
                .zoom(14)
                .bearing(0)
                .build();

        // Camera that makes reference to the maps view
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.animateCamera(cameraUpdate, 3000, new GoogleMap.CancelableCallback() {

            @Override
            public void onFinish() {
                Log.i(LOG_MAP, "googleMap.animateCamera:onFinish is active");
            }

            @Override
            public void onCancel() {
                Log.i(LOG_MAP, "googleMap.animateCamera:onCancel is active");
            }});
    }

    /** Step 3 - Reusable code
     This method is called everytime the use wants to place a new marker on the map. **/
    private void createCustomMapMarkers(LatLng latlng, String title, String snippet){

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng) // coordinates
                .title(title) // location name
                .snippet(snippet); // location description

        // Update the global variable (currentMapMarker)
        currentMapMarker = mMap.addMarker(markerOptions);
    }

    // Step 4 - Define a new marker based on a Map click (uses onMapClickListener)
    private void useMapClickListener(){

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latltn) {
                Log.i(LOG_MAP, "setOnMapClickListener");

                if(currentMapMarker != null){
                    // Remove current marker from the map.
                    currentMapMarker.remove();
                }
                // The current marker is updated with the new position based on the click.
                createCustomMapMarkers(
                        new LatLng(latltn.latitude, latltn.longitude),
                        "New Marker",
                        "Listener onMapClick - new position"
                                +"lat: "+latltn.latitude
                                +" lng: "+ latltn.longitude);
            }
        });
    }

    // Step 5 - Use OnMarkerClickListener for displaying information about the MapLocation
    private void useMarkerClickListener(){
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            // If FALSE, when the map should have the standard behavior (based on the android framework)
            // When the marker is clicked, it wil focus / centralize on the specific point on the map
            // and show the InfoWindow. IF TRUE, a new behavior needs to be specified in the source code.
            // However, you are not required to change the behavior for this method.
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(LOG_MAP, "setOnMarkerClickListener");

                return false;
            }
        });
    }

    private void useMapLongClickListener() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.i(LOG_MAP, "setOnMapLongClickListener");

                Toast.makeText(getApplicationContext(), "Clicked on Lat: " + latLng.latitude + ", Long: " + latLng.longitude, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void useCameraMoveListener() {
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                Log.i(LOG_MAP, "setOnCameraMoveStartedListener");

                Toast.makeText(getApplicationContext(), "Camera moving.... away we go!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createMarkersFromFirebase(){

        ArrayList<MapLocation> mapLocations = loadData();

        // List is always empty here because Firebase call is asynchronous
//        for (MapLocation location : mapLocations) {
//            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//            createCustomMapMarkers(latLng, location.getTitle(), location.getDescription());
//        }
    }

    private ArrayList<MapLocation> loadData(){


        final ArrayList<MapLocation> mapLocations = new ArrayList<>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.child("Cities");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MapLocation location = ds.getValue(MapLocation.class);
                    mapLocations.add(location);
                    Log.d(LOG_MAP, "Loaded data: " + location.getTitle() + ", Lat: " + location.getLatitude() + ", Long: " + location.getLongitude());

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng) // coordinates
                            .title(location.getTitle()) // location name
                            .snippet(location.getDescription()); // location description

                    // Update the global variable (currentMapMarker)
                    mMap.addMarker(markerOptions);

                    triggerBroadcastMessageFromFirebase(location.getTitle(), location.getLatitude(), location.getLongitude());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_MAP, "onCancelled", databaseError.toException());
            }
        });

        return mapLocations;
    }

    private void triggerBroadcastMessageFromFirebase(String location, Double lat, Double lng) {
        Intent explicitIntent = new Intent(this, BroadcastReceiverMap.class);
        Double latitude = Double.valueOf(lat);
        Double longitude = Double.valueOf(lng);

        explicitIntent.putExtra("LATITUDE", latitude);
        explicitIntent.putExtra("LONGITUDE", longitude);
        explicitIntent.putExtra("LOCATION", location);

        sendBroadcast(explicitIntent);
    }
}
