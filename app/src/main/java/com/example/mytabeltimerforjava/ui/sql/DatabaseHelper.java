package com.example.mytabeltimerforjava.ui.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mytabeltimerforjava.ui.home.Course;

import java.util.ArrayList;
import java.util.List;

// 数据库帮助类
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db"; // 数据库名称
    private static final int DATABASE_VERSION = 1; // 数据库版本号

    // 表名
    private static final String TABLE_COURSES = "courses";

    // 列名
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_ROOM = "room";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建课程表
        String createTable = "CREATE TABLE " + TABLE_COURSES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_ROOM + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级数据库，删除旧表并创建新表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    // 插入课程记录
    public long insertCourse(String name, String time, String room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_ROOM, room);

        // 插入记录并返回新行的 ID
        return db.insert(TABLE_COURSES, null, values);
    }

    // 查询所有课程
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // 查询课程表
        Cursor cursor = db.query(TABLE_COURSES, null, null, null, null, null, null);

        // 遍历游标获取数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
            String room = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM));
            Course course = new Course(name, time, room, 0); // 颜色设置为 0，可以根据需要修改
            course.setId(id); // 设置课程 ID
            courseList.add(course);
        }
        cursor.close();
        return courseList;
    }

    // 根据 ID 更新课程
    public void updateCourse(int id, String name, String time, String room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_ROOM, room);

        // 更新指定 ID 的课程
        db.update(TABLE_COURSES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }


    // 根据 ID 删除课程
    public void deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
