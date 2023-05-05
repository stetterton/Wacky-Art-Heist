/*
    Sarah Tetterton
    CPSC 1060: Rpg project
    Github Repository: Wacky-Art-Heist
    Lab Section: 3
    5/4/2023
*/
import java.util.HashMap;
public class Map{
    HashMap<String, Location> map;

    public Map(){
        map = new HashMap<>();
    }

    /*
     *Adds a location to the map
     *@param location- a place the user can go
    */ 
    public void addLocation(Location location){
        map.putIfAbsent(location.getName().toLowerCase(), location);
    }

    public Location getLocation(String locName){
        return map.get(locName.toLowerCase());
    }
}