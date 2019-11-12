package com.maxwinbergtest.model.event;

public interface Subscriber{

    //Here i could have made it more generic to send objects that the subscriber knows how to handle if they have subscribed for specific events.
    //Could also have added a enum with the given events that is possible.
    public void receiveEvent(String event, String message);
    public String[] getSubscriptions();
}