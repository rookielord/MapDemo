package com.zhd.mapdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.zhd.mapdemo.R;

/**
 * Created by 2015032501 on 2015/8/10.
 */
public class BaiduActivity extends Activity {

    MapView mMapview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        mMapview= (MapView) findViewById(R.id.map_view);
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
