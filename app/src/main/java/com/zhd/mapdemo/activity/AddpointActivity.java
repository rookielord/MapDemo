package com.zhd.mapdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zhd.mapdemo.R;

import java.util.List;

/**
 * Created by 2015032501 on 2015/8/6.
 * 将当前位置获取到，这个交给子线程去做，然后存储交给主线程
 */
public class AddpointActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpoint);
    }

}
