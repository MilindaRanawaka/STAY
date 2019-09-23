package com.example.stay;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String approvalAID="AcceptID";
    public static final String approvalAName="Accept";
    public static final String approvalRID="RejectID";
    public static final String approvalRName="Reject";

    private NotificationManager nManager;
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createApproval();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createApproval(){
        NotificationChannel accept=new NotificationChannel(approvalAID,approvalAName, NotificationManager.IMPORTANCE_DEFAULT);
        accept.enableLights(true);
        accept.enableVibration(true);
        accept.setLightColor(R.color.colorPrimary);
        accept.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(accept);


        NotificationChannel reject=new NotificationChannel(approvalRID,approvalRName, NotificationManager.IMPORTANCE_DEFAULT);
        reject.enableLights(true);
        reject.enableVibration(true);
        reject.setLightColor(R.color.colorPrimary);
        reject.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(reject);
    }

    public NotificationManager getManager(){
        if(nManager==null){
            nManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return nManager;
    }

    public NotificationCompat.Builder getAcceptNotification(){
        return new NotificationCompat.Builder(getApplicationContext(), approvalAID).setContentText("Accepted Food Order").setSmallIcon(R.drawable.ic_accept);
    }

    public NotificationCompat.Builder getRejectNotification(){
        return new NotificationCompat.Builder(getApplicationContext(), approvalRID).setContentText("Rejected Food Order").setSmallIcon(R.drawable.ic_reject);
    }


}
