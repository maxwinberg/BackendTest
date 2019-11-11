package com.maxwinbergtest.view;

import com.maxwinbergtest.model.event.EventBusHandler;
import com.maxwinbergtest.model.event.Subscriber;

public class ConsoleView implements Subscriber{

    public ConsoleView(){
        System.out.println("ConsoleView Started");
        EventBusHandler.getInstance().addSubscriber(this);
    }


    public void receiveEvent(String message) {
        System.out.println("New database Message!");
        System.out.println("Message: " + message);
    }

    public String[] getSubscriptions() {
        return new String[] { "Database", "RESTApi"};
    }
}