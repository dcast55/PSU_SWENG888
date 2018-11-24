package edu.psu.sweng888.assignment_maps_castellucci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.psu.sweng888.assignment_maps_castellucci.broadcast.BroadcastReceiverMap;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText editTextLat;
    private EditText editTextLong;
    private EditText editTextLocation;

    private Button buttonNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLat = findViewById(R.id.edit_text_lat);
        editTextLong = findViewById(R.id.edit_text_long);
        editTextLocation = findViewById(R.id.edit_text_location);

        buttonNavigate = findViewById(R.id.button_navigate);

        buttonNavigate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // Broadcast Receiver
        Intent explicitIntent = new Intent(this, BroadcastReceiverMap.class);
        Double latitude = Double.valueOf(editTextLat.getText().toString());
        Double longitude = Double.valueOf(editTextLong.getText().toString());
        String location = editTextLocation.getText().toString();

        explicitIntent.putExtra("LATITUDE", latitude);
        explicitIntent.putExtra("LONGITUDE", longitude);
        explicitIntent.putExtra("LOCATION", location);

        sendBroadcast(explicitIntent);

        // Navigating to MapActivity
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        intent.putExtra("LOCATION", location);

        startActivity(intent);
    }
}
