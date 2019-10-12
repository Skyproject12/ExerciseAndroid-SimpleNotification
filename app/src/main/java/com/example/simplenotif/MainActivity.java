package com.example.simplenotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID=1;
    public static String CHANNEl_ID= "channel_01";
    public static CharSequence CHANNEL_NAME="dicoding channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create notification in oncreate
        NotificationManager mNotificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(this,CHANNEl_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                // muncul icon di sebelah kiri
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            // ketika version android lebih dari android o
            NotificationChannel channel= new NotificationChannel(CHANNEl_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            // menggunakan channelname dan channel id untuk membuat suatu notification
            mBuilder.setChannelId(CHANNEl_ID);
            // jika tidak null maka create notification
            if(mNotificationManager!=null){
                mNotificationManager.createNotificationChannel(channel);
            }
        }
        Notification notification= mBuilder.build();
        if(mNotificationManager!=null){
            // jika notification tidak null maka create notification
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
    // content intent digunakan ketika action notification di tekan
}
