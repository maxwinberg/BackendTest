package com.maxwinbergtest.model.christmas;

public class ChristmasPresent {

    private String presentName;
    private float cost;
    private String store;

    ChristmasPresent(){}

    public ChristmasPresent(String presentName, float cost, String store){
        this.presentName = presentName;
        this.cost = cost;
        this.store = store;
    }

    public String getPresentName(){
        return this.presentName;
    }

    public String getStore(){
        return this.store;
    }

    public float getCost(){
        return this.cost;
    }

    public void setPresentName(String presentName){
        this.presentName = presentName;
    }

    public void setCost(float cost){
        this.cost = cost;
    }

    public void setStore(String store){
        this.store = store;
    }
}
