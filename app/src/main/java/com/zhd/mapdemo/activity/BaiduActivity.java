package com.zhd.mapdemo.activity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.zhd.mapdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2015032501 on 2015/8/10.
 */
public class BaiduActivity extends Activity {

    MapView mMapview = null;
    //使用安卓自带的方式定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        mMapview= (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected void onResume() {
        mMapview.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapview.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapview.onDestroy();
        super.onDestroy();
    }

}
