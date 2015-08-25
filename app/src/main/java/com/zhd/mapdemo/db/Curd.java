package com.zhd.mapdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Curd
 * 该类用于对数据库中的表进行操作
 * Created by 2015032501 on 2015/8/6.
 */
public class Curd {
    private String mTablename;
    private MySqliteOpenHelper mSQoh;
    private SQLiteDatabase mDb;

    public Curd(String tablename, Context context) {
        mTablename = tablename;
        mSQoh = new MySqliteOpenHelper(context);
        mDb=mSQoh.getWritableDatabase();

    }

    //插入操作
    public boolean insertData(List<ContentValues> cvList) {
        long res;
        for (ContentValues cv : cvList) {
            res = mDb.insert(mTablename, null, cv);
            if (res == -1)
                return false;
        }
        return true;
    }

    //删除操作
    public Boolean deleteData(String id) {
//        SQLiteDatabase sd = mSQoh.getWritableDatabase();
        int res = mDb.delete(mTablename, "id=?", new String[]{id});
        if (res != 1) {
            return false;
        }
        return true;
    }

    //修改操作
    public Boolean UpdateData(String id, ContentValues cv) {
//        SQLiteDatabase sd = mSQoh.getWritableDatabase();
        int res = mDb.update(mTablename, cv, "id=?", new String[]{id});
        if (res == 0) {
            return false;
        } else {
            return true;
        }
    }

    //查询操作
    public Cursor queryData(String[] columns, String selection,
                          String[] selectionArgs, String groupBy, String having,
                          String orderBy) {
        SQLiteDatabase sd = mSQoh.getReadableDatabase();
        Cursor cursor = sd.query(mTablename, columns, selection, selectionArgs,
                groupBy, having, orderBy);
        return cursor;

    }
}

