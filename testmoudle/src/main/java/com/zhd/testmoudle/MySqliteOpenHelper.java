package com.zhd.testmoudle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 2015032501 on 2015/8/6.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public MySqliteOpenHelper(Context context,int version) {
        super(context, "my.db", null, version);
    }

    /**
     * 在创建数据库时会创建的中英对照表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE test(" +
                "id integer primary key autoincrement," +
                "name varchar(20) ," +
                "age tinyint(3) )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
