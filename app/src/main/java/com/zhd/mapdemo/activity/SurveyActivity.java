package com.zhd.mapdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.zhd.mapdemo.R;

import java.util.List;

/**
 * Created by 2015032501 on 2015/8/20.
 */
public class SurveyActivity extends Activity implements View.OnClickListener {
    /**
     * 1.用来获得当前的GPS的坐标,并显示到前台界面
     * 2.并且将当前gps坐标转化为百度坐标，并显示在百度地图上，然后点击采集后会有一个标记
     * 3.
     * Created by 2015032501 on 2015/8/6.
     */
    private LocationManager mLocationManager;//位置管理者管理者
    private String mProvider;//提供位置的提供器（网络和GPS）
    private MyLocationListener myLocationListener = new MyLocationListener();//监听对象用来
    private TextView tv_info;//当前点的位置信息
    private Location mlocation;//获取到的当前位置
    private Button btn_add,btn_loc,btn_layer;


    //百度定位
    LocationClient mClient;//定位客户端
    MapView mMapview;//地图
    BaiduMap mBaiduMap;//百度地图
    MyListener myListener = new MyListener();//继承了百度监听的位置监听事件
    boolean isFirstLoc=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_survey);
        //获取控件
        btn_add= (Button) findViewById(R.id.btn_add_point);
        btn_loc= (Button) findViewById(R.id.btn_loc);
        btn_layer= (Button) findViewById(R.id.btn_change_layer);
        tv_info = (TextView) findViewById(R.id.tv_position);
        //对两个按钮绑定事件
        btn_loc.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_layer.setOnClickListener(this);
        //获取那里的地图对象
        mMapview = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapview.getMap();


        //开始百度定位
        mBaiduMap.setMyLocationEnabled(true);
        mClient = new LocationClient(this);
        mClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mClient.setLocOption(option);
        mClient.start();

        //使用GPS服务获取当前GPS数据
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        //只通过两种方式定位
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            mProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有对应的定位提供系统", Toast.LENGTH_LONG).show();
        }
        //对位置变化绑定监听事件
        mLocationManager.requestLocationUpdates(mProvider, 100, 1, myLocationListener);
        //对位置进行获取
        mlocation = mLocationManager.getLastKnownLocation(mProvider);
        if (mlocation != null) {
            String info = showLocation(mlocation);
            tv_info.setText(info);
        }
    }

    /**
     * 三个按钮来
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_loc:
                    //需要将地图模式设为跟随(定位)，然后在变为普通
                mBaiduMap
                        .setMyLocationConfigeration(new MyLocationConfiguration(
                                MyLocationConfiguration.LocationMode.FOLLOWING, true, null));
                mBaiduMap
                        .setMyLocationConfigeration(new MyLocationConfiguration(
                                MyLocationConfiguration.LocationMode.NORMAL, true, null));
                break;
            case R.id.btn_add_point:
                Intent intent=new Intent(this,AddpointActivity.class);
                intent.putExtra("N",mlocation.getLatitude());
                intent.putExtra("E",mlocation.getLongitude());
                intent.putExtra("Z",mlocation.getAltitude());
                startActivity(intent);
                break;
            case R.id.btn_change_layer:
                String msg=btn_layer.getText().toString();
                if (msg=="普通地图"){
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    btn_layer.setText("卫星图");
                }
                else {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    btn_layer.setText("普通地图");
                }

                break;
        }

    }

    /**
     * 这里通过将GPS坐标转化为百度地图能显示的坐标。但是再使用百度进行监听的时候已经定位了，这样造成了双重定位的后果
     * 使得百度地图不时会又自动对中。
     * 在这里可以不用将gps数据转化为百度地图定位。而只是使用百度地图的定位，这里只负责数据的获取。
     */
    class MyLocationListener implements LocationListener {

        //返回值为空，只能在这里进行入库操作
        @Override
        public void onLocationChanged(Location location) {
            //获取现在的位置信息
            String info = showLocation(location);
            tv_info.setText(info);
            //转化为百度地图的坐标
//            CoordinateConverter converter=new CoordinateConverter();
//            converter.from(CoordinateConverter.CoordType.GPS);
//            LatLng original=new LatLng(location.getLatitude(),location.getLongitude());
//            converter.coord(original);
//            LatLng after=converter.convert();//得到转化坐标
//            //在地图上显示
//            MapStatusUpdate u=MapStatusUpdateFactory.newLatLng(after);
//            mBaiduMap.animateMapStatus(u);
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

    private String showLocation(Location location) {
        double latitude = location.getLatitude();//纬度
        double longitude = location.getLongitude();//经度
        double altitude = location.getAltitude();//大地高
        String info =
                "当前纬度是:" + latitude + "\n" +
                "当前经度是:" + longitude + "\n" +
                "当前的大地高为" + altitude;
        return info;
    }

    class MyListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mMapview == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }
    }

    @Override
    protected void onPause() {
        mMapview.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapview.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        //如果定位服务不为空，就注销
        if (mLocationManager!=null){
            mLocationManager.removeUpdates(myLocationListener);
        }
        //注销百度定位
        mClient.stop();
        //关闭定位图层注销我的定位
        mBaiduMap.setMyLocationEnabled(false);
        mMapview.onDestroy();
        mMapview=null;
        //

        super.onDestroy();
    }
}

