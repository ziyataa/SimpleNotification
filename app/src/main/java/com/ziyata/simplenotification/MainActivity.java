package com.ziyata.simplenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitle, edtContent;
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNE_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "idn channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edt_title);
        edtContent = findViewById(R.id.edt_content);

    }

    public void sentNotification(View view) {
        // Mensetting notificationManager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNE_ID)
                .setSmallIcon(R.drawable.ic_add_alert)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_add_alert))
                .setContentTitle(edtTitle.getText().toString())
                .setContentText(edtContent.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        //Untuk android oreo ke atas perlu menambahkan notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNE_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNE_ID);
            if (mNotificationManager != null){
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        if (mNotificationManager != null){
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }

    }
}
