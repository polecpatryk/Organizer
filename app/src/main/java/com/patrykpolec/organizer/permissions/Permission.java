package com.patrykpolec.organizer.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

// Insert in AndroidManifest.xml before application tag
// <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
// <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

public class Permission {

    public void MemoryPermission(Activity activity, Context context, int access) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ContextCompat.checkSelfPermission(context , Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, access);
            }
        }
    }
}