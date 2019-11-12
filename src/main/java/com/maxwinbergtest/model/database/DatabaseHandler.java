package com.maxwinbergtest.model.database;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.List;
import com.maxwinbergtest.model.christmas.ChristmasPresent;


public class DatabaseHandler{

    private static DatabaseHandler instance = null;

    private Fongo fongo;
    private DB wishDB;
    private DBCollection wishlist;

    private DatabaseHandler(){
        System.out.println("DatabaseHandler started");

        this.fongo = new Fongo("mongo server 1");

        this.wishDB = this.fongo.getDB("wishdb");
        this.wishlist = this.wishDB.getCollection("wishcollection");
        System.out.println("Database connection up and running");
        System.out.println("Databa: "+this.wishlist.count());
    }

    public static DatabaseHandler getInstance(){
        if(instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

    public void saveWish(ChristmasPresent wish){
        BasicDBObject field = new BasicDBObject("name", 1);
        BasicDBObject wishDBObject = new BasicDBObject("name", wish.getPresentName());
        wishDBObject.put("store", wish.getStore());
        wishDBObject.put("cost", wish.getCost());
        if (this.wishlist.find(wishDBObject, field) == null){
            this.wishlist.insert(wishDBObject);
        }
    }

    public List<DBObject> getAllWishes(){
        return this.wishlist.find().toArray();
    }

    public DBObject getWish(String name){
        BasicDBObject field = new BasicDBObject("name", 1);
        BasicDBObject wishQuery = new BasicDBObject("name", name);
        return this.wishlist.find(wishQuery, field).curr();
    }

}