package com.maxwinbergtest.model.database;




public class DatabaseHandler{

    private static DatabaseHandler instance = null;

    private DatabaseHandler(){
        System.out.println("DatabaseHandler started");
    }

    public static DatabaseHandler getInstance(){
        if(instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

}