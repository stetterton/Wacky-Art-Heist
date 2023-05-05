/*
    Sarah Tetterton
    CPSC 1060: Rpg project
    Github Repository: Wacky-Art-Heist
    Lab Section: 3
    5/4/2023
*/
import java.util.ArrayList;
public class Location {
    String name;
    String description;
    ArrayList<String> otherRooms;
    Art art;
    int risk;

    public Location(String name, String description, Art art){
        this.name = name;
        this.description = description;
        this.art = art;
        this. otherRooms = new ArrayList<String>();
    }

    public String getName(){
        return this.name;
    }
    public ArrayList<String> getOtherRooms() {
        return this.otherRooms;
    }
    public String getDescription(){
        return this.description;
    }
    public Art getArt(){
        return this.art;
    }

    public void setRisk(int risk){
        this.risk = risk;
    }
    public int getRisk (){
        return this.risk;
    }


    public void addOtherRoom(String otherRoom){
        otherRooms.add(otherRoom);
    }
   
    /*
     *Lists what the user is able to do and where they can go from cloc
     *@return options- A string that lists the options
    */ 
    public String showOptions(){
        String options = "";
        if(art.getPrice() > 0){
            for(String o: otherRooms){
                options += o + "\n";
            }
            options += "Steal\n";

        }
        else {
            for(String o: otherRooms){
                options += o + "\n";
            }  
        }
        options += "Inventory";
        
        return options;
    }

    /*
     *Gives the user information on what room they are in
     *@return info- the rooms information
    */ 
    public String showRoomInfo(){
        String info = "";
        if(art.getPrice() > 0) {
            info = "You have entered the " +getName() + " exhibit. A guide tells the tourists: " + getDescription() +" There is a " + art.getArtPeice() + " here.";
        }
        else {
            info = "You have entered the " +getName() + " exhibit. A guide tells the tourists: " + getDescription();
        }
        return info;
    }

    /*
     *Tells users that they have gone back to the enterence and asks if they want to leave
     *@return str- the message
    */ 
    public String showEnterence(){
        String str = "";
        str = "You have gone back to the " + getName() + ". Are you ready to leave?";
        return str;
    }
}