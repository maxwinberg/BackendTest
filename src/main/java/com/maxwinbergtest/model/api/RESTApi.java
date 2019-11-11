package com.maxwinbergtest.model.api;

public class RESTApi{

    private static RESTApi instance = null;

    private RESTApi(){
        System.out.println("REST API have started");
    }

    public static RESTApi getInstance(){
        if(instance == null){
            instance = new RESTApi();
        }
        return instance;
    }
}
