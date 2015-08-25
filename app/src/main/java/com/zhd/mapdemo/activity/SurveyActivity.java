package com.zhd.mapdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.zhd.mapdemo.R;

import java.util.List;

/**
 * Created by 2015032501 on 2015/8/20.
 */
public class SurveyActivity extends Activity {
    /**
     * 用来获得当前的GPS的坐标,并显示到前台界面
     * Created by 2015032501 on 2015/8/6.
     */
    private LocationManager mLocationManager;//位置管理者管理者
    private String mProvider;//提供位置的提供器（网络和GPS）
    private MyLocationListener listener = new MyLocationListener();//监听对象用来
    private Location mlocation;
    private Handler mHandler=new Handler();//通过这个来开启子进程来定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            mProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有对应的定位提供系统", Toast.LENGTH_LONG).show();
        }
        //对位置变化绑定监听事件
        mLocationManager.requestLocationUpdates(mProvider, 1000, 1, listener);
        //对位置进行获取
        mlocation = mLocationManager.getLastKnownLocation(mProvider);
    }

    class MyLocationListener implements LocationListener {

        //返回值为空，只能在这里进行入库操作
        @Override
        public void onLocationChanged(Location location) {
            //获取现在的位置信息
            double latitude=location.getLatitude();//纬度
            double longitude=location.getLongitude();//经度
            double altitude=location.getAltitude();//大地高
                    
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}

