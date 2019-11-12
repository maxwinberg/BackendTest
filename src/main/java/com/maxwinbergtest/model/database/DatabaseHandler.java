package com.maxwinbergtest.model.database;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;


public class DatabaseHandler{

    private static DatabaseHandler instance = null;

    private DatabaseHandler(){
        System.out.println("DatabaseHandler started");

        Fongo fongo = new Fongo("mongo server 1");

        // once you have a DB instance, you can interact with it
        // just like you would with a real one.
        DB db = fongo.getDB("mydb");
        DBCollection collection = db.getCollection("mycollection");
        collection.insert(new BasicDBObject("name", "jon"));
    }

    public static DatabaseHandler getInstance(){
        if(instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

}