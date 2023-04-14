package com.example.ecommerceapp.model;

public class Notification {
    private String id;
    private String title;
    private String image;
    private String content;
    private NotificationState notificationState;

    public Notification() {
    }

    public Notification(String id, String title, String image, String content, NotificationState notificationState) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.notificationState = notificationState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationState getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(NotificationState notificationState) {
        this.notificationState = notificationState;
    }
}
