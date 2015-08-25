package com.zhd.mapdemo.model;

/**
 * Created by 2015032501 on 2015/8/13.
 */
public class Project {
    private String mCname;
    private String mEname;
    private int mID;

    public Project(String mCname, String mEname, int mID) {
        this.mCname = mCname;
        this.mEname = mEname;
        this.mID = mID;
    }

    public String getmEname() {
        return mEname;
    }

    public void setmEname(String mEname) {
        this.mEname = mEname;
    }

    public String getmCname() {
        return mCname;
    }

    public void setmCname(String mCname) {
        this.mCname = mCname;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }
}
