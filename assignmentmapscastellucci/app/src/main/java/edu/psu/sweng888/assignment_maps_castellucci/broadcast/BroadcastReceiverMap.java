package edu.psu.sweng888.assignment_maps_castellucci.broadcast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import edu.psu.sweng888.assignment_maps_castellucci.R;


public class BroadcastReceiverMap extends BroadcastReceiver {

    public static final String MAP_LOCATION_BROADCAST = "edu.psu.sweng888.assignment_maps_castellucci.MAP_LOCATION_BROADCAST";

    public static final int CHANNEL_ID = 1;
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    public static final String CHANNEL_DESCRIPTION = "BROADCAST MAP CHANNEL";
    public static final String CHANNEL_NAME = "MAPS";

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {
        Double latitude = intent.getDoubleExtra("LATITUDE", Double.NaN);
        Double longitude = intent.getDoubleExtra("LONGITUDE", Double.NaN);
        String location = intent.getStringExtra("LOCATION");

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        builder = new NotificationCompat.Builder(context, CHANNEL_NAME);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(location);
        builder.setContentText("Located at " + latitude + ", " + longitude);

        notificationManager.createNotificationChannel(getNotificationChannel());

        notificationManager.notify(CHANNEL_ID, builder.build());
    }

    private NotificationChannel getNotificationChannel(){

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_NAME, CHANNEL_DESCRIPTION, CHANNEL_IMPORTANCE);
        notificationChannel.setDescription(CHANNEL_DESCRIPTION);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setShowBadge(true);

        return notificationChannel;
    }
}
