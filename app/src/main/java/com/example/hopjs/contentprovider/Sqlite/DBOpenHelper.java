package com.example.hopjs.contentprovider.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*目的：了解SQLite数据库的使用方法
* 结果：
* 1，创建一个继承自SQLiteOpenHelper的数据库操作类
* 2，重写它的构造方法、onCreate方法和onUpdate方法
* 在构造方法中定义数据库名称和版本
* 在onCreate方法中创建数据表
* 在onUpdate方法中对数据库进行更新
* 3，对数据库表进行增删改查操作请看PersonService类*/
public class DBOpenHelper extends SQLiteOpenHelper {
    // 类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String DBNAME = "ljq.db";
    private static final int VERSION = 1;

    // 第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,
    // 设置为null,代表使用系统默认的工厂类
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PERSON (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), PHONE VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 注：生产环境上不能做删除操作
        db.execSQL("DROP TABLE IF EXISTS PERSON");
        onCreate(db);
    }
}