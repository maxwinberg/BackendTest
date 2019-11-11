package com.maxwinbergtest.model.event;

import java.util.HashMap;
import java.util.ArrayList;
import com.maxwinbergtest.model.event.Subscriber;

public class EventBusHandler{

    // static variable single_instance of type Singleton
    private static EventBusHandler instance = null;

    private HashMap<String, ArrayList<Subscriber>> subscribers;

    // private constructor restricted to this class itself
    private EventBusHandler()
    {

        subscribers = new HashMap<String, ArrayList<Subscriber>>();

    }

    // static method to create instance of Singleton class
    public static EventBusHandler getInstance()
    {
        if (instance == null)
            instance = new EventBusHandler();

        return instance;
    }

    public void addSubscriber(Subscriber subscriber){

    }
}