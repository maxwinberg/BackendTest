package com.maxwinbergtest.model.event;

public interface Subscriber{

    public void receiveEvent(String message);
    public String[] getSubscriptions();
}