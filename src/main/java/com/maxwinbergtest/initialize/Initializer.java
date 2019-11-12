package com.maxwinbergtest.initialize;

import com.maxwinbergtest.model.api.RESTApi;
import com.maxwinbergtest.model.database.DatabaseHandler;
import com.maxwinbergtest.model.event.EventBusHandler;
import com.maxwinbergtest.view.ConsoleView;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
}, scanBasePackages = {"com.maxwinbergtest.model.api"})
public class Initializer{

    public Initializer(){

        //Starting up the program
        System.out.println("Starting Max Winberg Backend Test");
        System.out.println("Heeey Inovia! Lets create great AI solutions!");

        //Initializing Singletons
        EventBusHandler.getInstance();
        DatabaseHandler.getInstance();

        //Init Views
        new ConsoleView();
    }

    public static void main(String[] args){

        //Add startup args if the program shall be able to start in different ways.
        if(args.length == 0){
            SpringApplication.run(Initializer.class, args);
            //new Initializer();
        }
    }
}