package com.zhd.testmoudle;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        Button btn_insert= (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqliteCurd curd=new SqliteCurd("test",MainActivity.this,1);
                ContentValues cv=new ContentValues();
                cv.put("name","奥创");
                cv.put("age", 33);
                boolean res=curd.insert(cv);
                Log.i("数据库插入", String.valueOf(res));
            }
        });
        Button btn_query= (Button) findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqliteCurd curd=new SqliteCurd("test",MainActivity.this,1);
                Cursor cursor=curd.query();
                StringBuilder sb=new StringBuilder();
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String age = cursor.getString(cursor.getColumnIndex("age"));
                        sb.append("名字是:" + name + "@@年龄是：" + age + "\n");
                    } while (cursor.moveToNext());
                }
                tv.setText(sb.toString());
            }
        });
        //Date curdate=new Date(System.currentTimeMillis());
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Date curdate=new Date(System.currentTimeMillis());
        String name=format.format(curdate);
        tv.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
