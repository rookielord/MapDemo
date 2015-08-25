package com.zhd.testmoudle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 2015032501 on 2015/8/13.
 */
public class SqliteCurd {
    private String mTableName;
    private MySqliteOpenHelper mine;

    public SqliteCurd(String tablename, Context context,int version) {
        mTableName = tablename;
        mine = new MySqliteOpenHelper(context,version);
    }

    public Cursor query() {
        SQLiteDatabase database = mine.getReadableDatabase();
        Cursor cursor = database.query(mTableName, null, null, null, null, null, null);
        return cursor;
    }

    public Boolean insert(ContentValues cv) {
        SQLiteDatabase database = mine.getWritableDatabase();
        long res = database.insert(mTableName, null, cv);
        if (res!=-1){
            return true;
        }
        else{
            return false;
        }
    }
}
