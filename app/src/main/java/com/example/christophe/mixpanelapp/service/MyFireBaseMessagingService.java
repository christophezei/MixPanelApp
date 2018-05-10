package com.example.christophe.mixpanelapp.service;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URISyntaxException;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    public static String KEY_NOTIF = "NOTIF";

    public MyFireBaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            String key = remoteMessage.getData().get("key");
            Intent intent = new Intent(KEY_NOTIF);
            intent.putExtra("value", key);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            switch (key) {
                case "2":
                    sendNotification();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNotification() {
        String messageBody = "You received a notification!";
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New message")
                .setContentText(messageBody)
                .setAutoCancel(true).setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());


    }


}
