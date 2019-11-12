package com.maxwinbergtest.model.api;


import com.maxwinbergtest.model.database.DatabaseHandler;
import com.maxwinbergtest.model.event.EventBusHandler;
import com.mongodb.util.JSON;
import org.springframework.web.bind.annotation.*;
import com.mongodb.DBObject;
import com.maxwinbergtest.model.christmas.ChristmasPresent;
import java.util.ArrayList;

@RestController
public class RESTApi{

    /**
     * This is the API, i could have spend more time for better error handling and better response format with the right codes 200 for example etc.
     *
     */

    private static final String hi = "Heeeey Inovia!!\n";
    private DatabaseHandler database;

    public RESTApi(){
        EventBusHandler.getInstance().sendEvent("RESTApi", "RESTApi started" );
        this.database = DatabaseHandler.getInstance();
    }

    @GetMapping(value = "/sayhi")
    public String sayhi(){
        EventBusHandler.getInstance().sendEvent("RESTApi", "Someone is saying hi!" );
        return hi;
    }

    @GetMapping("/wishlist")
    public String all(){
        ArrayList<String> JSONFormat = new ArrayList<String>();
        //I want some nice formating instead of returning the list with DBObjects from DB, has alot of timestamps etc in it.
        for(DBObject wish: this.database.getAllWishes()){
            JSONFormat.add(JSON.serialize(wish)+"\n");
        }
        EventBusHandler.getInstance().sendEvent("RESTApi", "Someone is checking out their wishes for christmas!" );
        if(!JSONFormat.isEmpty()){
            return JSONFormat.toString();
        }else{
            return "Your wishlist is empty! go ahead and /makeawish before Santa delivers all presents!\n";
        }

    }

    @PostMapping(value = "/makeawish", consumes = "application/json", produces = "application/json")
    public String createWish(@RequestBody ChristmasPresent wish){

        try{
            this.database.saveWish(wish);
            EventBusHandler.getInstance().sendEvent("RESTApi", "Contact Santa, someone have made a new wish! Present: " + wish.getPresentName() + " Cost: " + wish.getCost() + " Store: " + wish.getStore());
            return "Cool wish, " + wish.getPresentName() + "! Santa added it to your wishlist!\n";
        }catch(Exception e){
            EventBusHandler.getInstance().sendEvent("ERROR", "ERROR IN API: " + e.getMessage());
            return "Santa is saying that you have been too naughty this year for this present, sorry.\n";
        }

    }

    @GetMapping("/wish/{name}")
    public String getWish(@PathVariable String name){

        EventBusHandler.getInstance().sendEvent("RESTApi", "Someone is interested in a specific wish: " + name);
        String results;
        try{
            results = JSON.serialize(this.database.getWish(name))+"\n";
        }catch(Exception e){
            results = "Couldnt find your wish, go ahead and /makeawish";
            EventBusHandler.getInstance().sendEvent("ERROR", "ERROR in RESTApi: " + e.getMessage());
        }

        return results;
    }
}
