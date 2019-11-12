package com.maxwinbergtest.model.database;

import com.github.fakemongo.Fongo;
import com.mongodb.*;

import java.util.List;
import com.maxwinbergtest.model.christmas.ChristmasPresent;

import com.maxwinbergtest.model.event.EventBusHandler;


public class DatabaseHandler{

    private static DatabaseHandler instance = null;

    private Fongo fongo;
    private DB wishDB;
    private DBCollection wishlist;

    private DatabaseHandler(){
        EventBusHandler.getInstance().sendEvent("Database", "DatabaseHandler started");
        try{
            this.fongo = new Fongo("mongo server 1");

            this.wishDB = this.fongo.getDB("wishdb");
            this.wishlist = this.wishDB.getCollection("wishcollection");
            EventBusHandler.getInstance().sendEvent("Database", "Connected to Database");
            EventBusHandler.getInstance().sendEvent("Database", "Current amount of wishes in DB: " + this.wishlist.count());
        }catch(Exception e){
            EventBusHandler.getInstance().sendEvent("ERROR", "ERROR CONNECTING TO DATABASE: \n" + e.toString());
        }

    }

    //Singelton getter!
    public static DatabaseHandler getInstance(){
        if(instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

    public void saveWish(ChristmasPresent wish) throws Exception{
        BasicDBObject field = new BasicDBObject("presentName", 1);
        BasicDBObject wishDBObject = new BasicDBObject("presentName", wish.getPresentName());
        wishDBObject.put("store", wish.getStore());
        wishDBObject.put("cost", wish.getCost());
        //Wont add the same present to the wishlist.
        if (this.wishlist.find(wishDBObject, field).count() == 0){
            EventBusHandler.getInstance().sendEvent("Database", ("Adding wish: " + wish.getPresentName() + " to the wish list"));
            this.wishlist.insert(wishDBObject);
            EventBusHandler.getInstance().sendEvent("Database", ("Santa have now " + this.wishlist.count() + " presents in wishlist"));
        }else{
            EventBusHandler.getInstance().sendEvent("Database", ("Couldnt add the wish " + wish.getPresentName() + " to the wishlist, it already exist"));
            throw new Exception("Santa already knows this wish");
        }
    }

    public List<DBObject> getAllWishes(){
        return this.wishlist.find().toArray();
    }

    public DBObject getWish(String name) throws Exception{

        BasicDBObject field = new BasicDBObject("presentName", 1);
        field.put("cost", 1);
        field.put("store", 1);
        BasicDBObject wishQuery = new BasicDBObject("presentName", name);
        DBCursor results = this.wishlist.find(wishQuery, field);
        EventBusHandler.getInstance().sendEvent("Database", ("Found: " + results.count() + " results in wishlist when searching for: " + name));
        if(results.count() > 0){
            return results.next();
        }else{
            throw new Exception("Couldnt find the wish: " + name);
        }

    }

}