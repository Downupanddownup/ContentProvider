package com.example.hopjs.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hopjs.contentprovider.Sqlite.PersonServiceTest;
/*
* 这个项目的作用：1，了解ContentProvidr 2，了解SQLite*/
/*目的：如何调用ContentProvider
* 结果：
* 1，创建ContentResolver，它与ContentProvider的方法基本一致
* 2，定义资源名
* 3，利用ContentResolver和资源名进行增删改查操作*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.hopjs.contentprovider/person");
        ContentValues c = new ContentValues();
        c.put("name","锋");
        c.put("phone","11111");
        contentResolver.insert(uri,c);

        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        while (cursor.moveToNext()){
            String[] names = cursor.getColumnNames();
            String tem="";
            tem = "id="+cursor.getInt(0)+" name="+cursor.getString(1)+" phone="+cursor.getString(2);
            Log.i("11111111111",tem);
        }
    }
}
