package com.maxwinbergtest.model.api;


import com.maxwinbergtest.model.database.DatabaseHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.mongodb.DBObject;
import com.maxwinbergtest.model.christmas.ChristmasPresent;
import java.util.List;
import java.util.ArrayList;

@RestController
public class RESTApi{

    private static final String hi = "Heeeey Inovia!!";
    private DatabaseHandler database;

    public RESTApi(){
        System.out.println("REST API have started");
        this.database = DatabaseHandler.getInstance();
    }

    //@RequestMapping(value = "/sayhi", method = RequestMethod.GET,
     //       produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/sayhi", method = RequestMethod.GET)
    public String sayhi(){
        System.out.println("CALLING SAY HI");
        return hi;
    }

    @GetMapping("/wishlist")
    List<DBObject> all(){
        return this.database.getAllWishes();
    }

    @PostMapping(value = "/makeawish", consumes = "application/json", produces = "application/json")
    public void createWish(@RequestBody ChristmasPresent wish){
        this.database.saveWish(wish);
    }

    @GetMapping("/wish/{name}")
    public DBObject getWish(@PathVariable String name){
        return this.database.getWish(name);
    }
}
