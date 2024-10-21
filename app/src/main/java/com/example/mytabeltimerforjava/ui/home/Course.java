package com.example.mytabeltimerforjava.ui.home;

public class Course {
    private String name;
    private String time;
    private String roomName;
    private int color; // 添加颜色属性

    public Course(String name, String time, String roomName, int color) {
        this.name = name;
        this.time = time;
        this.roomName = roomName;
        this.color = color; // 初始化颜色
    }

    // 添加 getter 方法
    public int getColor() {
        return color;
    }

    // 其他 getter 方法
    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
