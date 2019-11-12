package com.maxwinbergtest.initialize;

import com.maxwinbergtest.model.api.RESTApi;
import com.maxwinbergtest.model.database.DatabaseHandler;
import com.maxwinbergtest.model.event.EventBusHandler;
import com.maxwinbergtest.view.ConsoleView;

import java.io.Console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Initializer{

    public Initializer(){

        //Starting up the program
        System.out.println("Starting Max Winberg Backend Test");
        System.out.println("Heeey Inovia! Lets create great AI solutions!");

        //Initializing Singletons
        EventBusHandler.getInstance();
        DatabaseHandler.getInstance();
        RESTApi.getInstance();

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