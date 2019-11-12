package com.maxwinbergtest.view;

import com.maxwinbergtest.model.event.EventBusHandler;
import com.maxwinbergtest.model.event.Subscriber;
import java.util.Date;

public class ConsoleView implements Subscriber{

    public ConsoleView(){
        System.out.println("ConsoleView Started");
        EventBusHandler.getInstance().addSubscriber(this);
    }


    public void receiveEvent(String event, String message) {
        Date time = new Date();
        if(event.contains("ERROR")){
            System.out.println("<!!WARNING!!"+time.toString()+" - "+ event +" !!WARNING!!>: " + message);
        }else{
            System.out.println("["+time.toString()+" - "+ event +" ]: " + message);
        }

    }

    public String[] getSubscriptions() {
        return new String[] { "Database", "RESTApi", "ERROR"};
    }
}