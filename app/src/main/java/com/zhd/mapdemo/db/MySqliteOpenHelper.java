package com.zhd.mapdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 2015032501 on 2015/8/6.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public MySqliteOpenHelper(Context context) {
        super(context, "points.db", null, 1);
    }

    /**
     * 在创建数据库时会创建的中英对照表
     * @param db
     */
    private static final String CREATE_PROJECT="CREATE TABLE project_table(" +
            "id integer primary key autoincrement," +
            "Cname varchar(20) not null," +
            "Ename varchar(32) not null);";
    private static final String CREATE_POINT="CREATE TABLE point_table(" +
            "id integer primary key autoincrement," +
            "name varchar(10) ," +
            "N varchar(32) ," +
            "E varchar(32) ," +
            "Z varchar(32) ," +
            "DES text);";
    private static final String CREATE_CONTROL_POINT="CREATE TABLE controlpoint_table(" +
            "id integer primary key autoincrement," +
            "name varchar(10)," +
            "N varchar(20)," +
            "E varchar(20)," +
            "Z varchar(20)," +
            "B varchar(20)," +
            "L varchar(20)," +
            "H varchar(20)," +
            "DES text);";
    private static final String CREATE_LAYOUT_POINT="CREATE TABLE layoutpoint_table(" +
            "id integer primary key autoincrement," +
            "name varchar(10)," +
            "N varchar(20)," +
            "E varchar(20)," +
            "Z varchar(20)," +
            "mileage varchar(20)," +
            "DES text);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROJECT);
        db.execSQL(CREATE_POINT);
        db.execSQL(CREATE_CONTROL_POINT);
        db.execSQL(CREATE_LAYOUT_POINT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
