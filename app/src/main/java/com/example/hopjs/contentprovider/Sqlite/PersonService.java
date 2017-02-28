package com.example.hopjs.contentprovider.Sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

public class PersonService {
    public DBOpenHelper dbOpenHelper = null;

    /**
     * 构造函数
     *
     * 调用getWritableDatabase()或getReadableDatabase()方法后，会缓存SQLiteDatabase实例；
     * 因为这里是手机应用程序，一般只有一个用户访问数据库，所以建议不关闭数据库，保持连接状态。
     * getWritableDatabase()，getReadableDatabase的区别是当数据库写满时，调用前者会报错，调用后者不会，
     * 所以如果不是更新数据库的话，最好调用后者来获得数据库连接。
     *
     * 对于熟悉SQL语句的程序员最好使用exeSQL(),rawQuery(),因为比较直观明了
     *
     * @param context
     */
    public PersonService(Context context){
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void save(Person person){
        dbOpenHelper.getWritableDatabase().execSQL("insert into person(name, phone) values (?, ?)",
                new Object[]{person.getName(), person.getPhone()});
    }

    public void update(Person person){
        dbOpenHelper.getWritableDatabase().execSQL("update person set name=?, phone=? where id=?",
                new Object[]{person.getName(), person.getPhone(), person.getId()});
    }

    public void delete(Integer... ids){
        if(ids.length>0){
            StringBuffer sb = new StringBuffer();
            for(Integer id : ids){
                sb.append("?").append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            dbOpenHelper.getWritableDatabase().execSQL("delete from person where id in ("+sb+")", (Object[])ids);
        }
    }

    public Person find(Integer id){
        Cursor cursor = dbOpenHelper.getReadableDatabase().rawQuery("select id, name, phone from person where id=?",
                new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            int personid = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            return new Person(personid, name, phone);
        }
        return null;
    }

    public long getCount(){
        Cursor cursor = dbOpenHelper.getReadableDatabase().query("person",
                new String[]{"count(*)"}, null,null,null,null,null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 分页
     *
     * @param startResult 偏移量，默认从0开始
     * @param maxResult 每页显示的条数
     * @return
     */
    public List<Person> getScrollData(int startResult, int maxResult){
        List<Person> persons = new ArrayList<Person>();
        //Cursor cursor = dbOpenHelper.getReadableDatabase().query("person", new String[]{"id, name, phone"},
        //        "name like ?", new String[]{"%ljq%"}, null, null, "id desc", "1,2");
        Cursor cursor = dbOpenHelper.getReadableDatabase().rawQuery("select * from person limit ?,?",
                new String[]{String.valueOf(startResult), String.valueOf(maxResult)});
        while(cursor.moveToNext()) {
            int personid = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            persons.add(new Person(personid, name, phone));
        }
        return persons;
    }



}