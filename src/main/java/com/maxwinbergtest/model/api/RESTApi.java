package com.maxwinbergtest.model.api;


import com.maxwinbergtest.model.database.DatabaseHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.maxwinbergtest.model.christmas.ChristmasPresent;
import java.util.List;
import java.util.ArrayList;

@RestController
public class RESTApi{

    private static RESTApi instance = null;
    private DatabaseHandler database;

    private RESTApi(){
        System.out.println("REST API have started");
        this.database = DatabaseHandler.getInstance();
    }

    public static RESTApi getInstance(){
        if(instance == null){
            instance = new RESTApi();
        }
        return instance;
    }

    @GetMapping("/wishlist")
    List<ChristmasPresent> all(){
        return new ArrayList<ChristmasPresent>();
    }

    @PostMapping(value = "/makeawish", consumes = "application/json", produces = "application/json")
    public ChristmasPresent createWish(@RequestBody ChristmasPresent wish){
        return this.database.saveWish(wish);
    }

    @GetMapping("/wish/{name}")
    public ChristmasPresent findwish(@PathVariable String name){
        return this.database.findWish(name);
    }
}
