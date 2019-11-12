package com.maxwinbergtest.model.event;

import java.util.HashMap;
import java.util.ArrayList;
import com.maxwinbergtest.model.event.Subscriber;


public class EventBusHandler{

    /**
     *  This class i could do much better, adding thread pool making the process able to continue after reported the event and not need to wait until
     *  all subscribers have acted on the event for example.
     *
     *  More generic event handling sending objects that the subscriber knows how to handle for example.
     *
     *  i could also connect a logger to this EventBus and recors all events to a file etc.
     *
     */

    // static variable single_instance of type Singleton
    private static EventBusHandler instance = null;

    private HashMap<String, ArrayList<Subscriber>> subscribers;

    // private constructor restricted to this class itself
    private EventBusHandler()
    {

        subscribers = new HashMap<String, ArrayList<Subscriber>>();

    }

    // Singelton getter function!
    public static EventBusHandler getInstance()
    {
        if (instance == null)
            instance = new EventBusHandler();

        return instance;
    }

    public void addSubscriber(Subscriber subscriber){
        for(String subscription: subscriber.getSubscriptions()){
            if(subscribers.get(subscription) == null){
                ArrayList<Subscriber> subscribersArr = new ArrayList<Subscriber>();
                subscribersArr.add(subscriber);
                subscribers.put(subscription, subscribersArr);
            }else{
                subscribers.get(subscription).add(subscriber);
            }
        }
    }

    public void sendEvent(String event, String message){
        if(this.subscribers.get(event) != null) {
            for (Subscriber subscriber : this.subscribers.get(event)) {
                subscriber.receiveEvent(event, message);
            }
        }
    }
}