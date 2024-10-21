package com.example.mytabeltimerforjava.ui.home;

public class Course {
    private int id;
    private String name;
    private String time;
    private String roomName;
    private int color; // 可以用于设置课程的颜色

    public Course(String name, String time, String roomName, int color) {
        this.name = name;
        this.time = time;
        this.roomName = roomName;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
