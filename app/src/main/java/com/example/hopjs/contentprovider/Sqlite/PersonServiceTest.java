package com.example.hopjs.contentprovider.Sqlite;

import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class PersonServiceTest extends AndroidTestCase{
    private final String TAG = "PersonServiceTest";

    private PersonService personService;
    public PersonServiceTest(Context context) {
        super();
        personService = new PersonService(context);
    }

    public void testSave() throws Exception{
        personService.save(new Person("zhangsan1", "059188893343"));
        personService.save(new Person("zhangsan2", "059188893343"));
        personService.save(new Person("zhangsan3", "059188893343"));
        personService.save(new Person("zhangsan4", "059188893343"));
        personService.save(new Person("zhangsan5", "059188893343"));
    }

    public void testUpdate() throws Exception{
        Person person = personService.find(1);
        person.setName("linjiqin");
        personService.update(person);
    }

    public void testFind() throws Exception{
        Person person = personService.find(1);
        Log.i(TAG, person.getName());
       // System.out.println("person.getName():"+person.getName());
    }

    public void testList() throws Exception{
        List<Person> persons = personService.getScrollData(0, 10);
        for(Person person : persons){
            Log.i(TAG, person.getId() + " : " + person.getName());
         //   System.out.println(person.getId() + " : " + person.getName());
        }
    }

    public void testCount() throws Exception{
        Log.i(TAG, String.valueOf(personService.getCount()));
      //  System.out.println(String.valueOf(personService.getCount()));
    }

    public void testDelete() throws Exception{
        personService.delete(1);
    }

    public void testDeleteMore() throws Exception{
        personService.delete(new Integer[]{2, 5, 6});
    }
}