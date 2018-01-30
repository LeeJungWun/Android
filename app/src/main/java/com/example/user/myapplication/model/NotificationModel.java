package com.example.user.myapplication.model;

/**
 * Created by MSL on 2018-01-17.
 */

public class NotificationModel {

    public String to;
    public Notification notification = new Notification();
    public Data data = new Data();

    public static class Notification{
        public String title;
        public String text;
    }

    public static class Data{
        public String title;
        public String text;
    }
}
