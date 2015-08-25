package com.zhd.mapdemo.activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhd.mapdemo.R;
import com.zhd.mapdemo.db.MySqliteOpenHelper;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_baidu, btn_survey, btn_project, btn_coordinate, btn_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到对应控件
        btn_project = (Button) findViewById(R.id.btn_project);
        btn_baidu = (Button) findViewById(R.id.btn_BD);
        btn_survey = (Button) findViewById(R.id.btn_survey);
        btn_coordinate = (Button) findViewById(R.id.btn_coordinate);
        btn_points = (Button) findViewById(R.id.btn_points);
        //分别绑定
        btn_project.setOnClickListener(this);
        btn_baidu.setOnClickListener(this);
        btn_survey.setOnClickListener(this);
        btn_coordinate.setOnClickListener(this);
        btn_points.setOnClickListener(this);
        //创建数据库
        MySqliteOpenHelper mine=new MySqliteOpenHelper(this);
        mine.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.btn_points:
                intent = new Intent(this, AddpointActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_project:
                intent = new Intent(this,ProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_BD:
                intent = new Intent(this,BaiduActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_survey:
                intent=new Intent("com.zhd.bdlocation");
                startActivity(intent);
                break;
            case R.id.btn_coordinate:
                intent=new Intent(this,CoordinateActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this,"操作错误",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}

