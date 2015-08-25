package com.zhd.mapdemo.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

/**
 * 用来获得当前的GPS的坐标,并显示到前台界面
 * Created by 2015032501 on 2015/8/6.
 */
public class MyLocation {
    private Context mContext;
    private LocationManager mLocationManager;//位置管理者管理者
    private String mProvider;//提供位置的提供器（网络和GPS）
    private MyLocationListener listener=new MyLocationListener();//监听对象用来
    private Location mlocation;
    public Location MyLocation(Context context) {
        //将Context对象赋值
        mContext=context;
        //获取服务
        mLocationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers=mLocationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)){
            mProvider=LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            mProvider=LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(mContext,"没有对应的定位提供系统",Toast.LENGTH_LONG).show();
        }
        //对位置变化绑定监听事件
        mLocationManager.requestLocationUpdates(mProvider,1000,1,listener);
        //对位置进行获取
        mlocation=mLocationManager.getLastKnownLocation(mProvider);

        return mlocation;
    }

    class MyLocationListener implements LocationListener{

        //返回值为空，只能在这里进行入库操作
        @Override
        public void onLocationChanged(Location location) {

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
