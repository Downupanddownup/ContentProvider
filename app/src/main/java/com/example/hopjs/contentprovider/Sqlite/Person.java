package com.example.hopjs.contentprovider.Sqlite;

/**
 * Created by Hopjs on 2017/2/3.
 */

public class Person {
    private Integer id;
    private String name;
    private String phone;

    public Person() {
        super();
    }

    public Person(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public Person(Integer id, String name, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
