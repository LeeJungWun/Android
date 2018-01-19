package com.example.user.myapplication.model;

import android.app.Notification;

/**
 * Created by MSL on 2018-01-17.
 */

public class NotificationModel {

    public String to;
    public Notification notification = new Notification();

    public static class Notification{
        public String title;
        public String text;
    }
}
