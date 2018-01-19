package com.example.user.myapplication.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MSL on 2018-01-11.
 */

public class ChatModel {

    public Map<String,Boolean> users = new HashMap<>(); //채팅방의 유저들
    public Map<String,Comment> comments = new HashMap<>();//채팅방의 대화내용

    public static class Comment {

        public String uid;
        public String message;
        public Object timestamp;
    }

}
