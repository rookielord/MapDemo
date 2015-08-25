package com.zhd.mapdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zhd.mapdemo.db.Curd;
import com.zhd.mapdemo.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2015032501 on 2015/8/12.
 */
public class ProjectManage extends Activity implements View.OnClickListener {
    private List<Project> projects = new ArrayList<Project>();
    private List<String> names;
    private Button btn_pro_add;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        //使用listView来对项目进行选择
        ListView lv = (ListView) findViewById(R.id.lv_project);
        btn_pro_add= (Button) findViewById(R.id.btn_pro_add);
        et= (EditText) findViewById(R.id.et_proname);
        btn_pro_add.setOnClickListener(this);
        //获取显示的
        ContentResolver cr = getContentResolver();
        Curd curd = new Curd("project_table", this);
        //将项目的名称全部查询出来
        Cursor cursor = curd.queryData(null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String Cname = cursor.getString(cursor.getColumnIndex("Cname"));
                String Ename = cursor.getString(cursor.getColumnIndex("Ename"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Project p = new Project(Cname, Ename, id);
                projects.add(p);
                names.add(Cname);
            } while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_project, R.id.proname, names);
            lv.setAdapter(adapter);
            //对每个item绑定点击事件
        }
    }

    @Override
    public void onClick(View v) {
        Curd curd=new Curd("project_table",this);
        List<ContentValues> valueslist=new ArrayList<ContentValues>();
        ContentValues cv=new ContentValues();

    }
}
