package com.example.hopjs.contentprovider;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;

import com.example.hopjs.contentprovider.Sqlite.DBOpenHelper;
import com.example.hopjs.contentprovider.Sqlite.Person;
import com.example.hopjs.contentprovider.Sqlite.PersonService;

/**
 * Created by Hopjs on 2017/2/3.
 */
/*
* 目的：了解自定义ContentProvider的使用方法
* 结果：
* 1，创建一个继承了ContentProvider的自定义的ContentProvider
* 2，定义它的资源名称
* 3，创建资源名称匹配器
* 4，创建一个数据库
* 5，重写它的六个方法 onCreate insert query delete update getType
* 在四个数据操作方法中，通过资源匹配器判断不同类型的数据请求，并处理
* 6，在配置文件中声明这个ContentProvider*/
public class MyContentProvider extends ContentProvider {
    public static final String AUTHORITY="com.example.hopjs.contentprovider";
    public static final Uri uri= Uri.parse("content://"+AUTHORITY+"/person");
    public PersonService p=null;
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"person",1);
    }
    @Override
    public boolean onCreate() {
        p=new PersonService(this.getContext());
        return false;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(uriMatcher.match(uri)==1){
            p.save(new Person(values.getAsString("name"),values.getAsString("phone")));
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri)==1){
            Cursor cursor = p.dbOpenHelper.getReadableDatabase().rawQuery(
                    "select id,name,phone from person",null);
            return cursor;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


}
