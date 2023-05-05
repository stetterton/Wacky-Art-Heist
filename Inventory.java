/*
    Sarah Tetterton
    CPSC 1060: Rpg project
    Github Repository: Wacky-Art-Heist
    Lab Section: 3
    5/4/2023
*/
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
public class Inventory{
    ArrayList<Art> collection;

    public Inventory(){
        this.collection = new ArrayList<Art>();
    }

    public ArrayList<Art> getCollection(){
        return this.collection;
    }

    /*
     *Puts an artwork into the user's inventory and sets the art's price to 0 to tell the program that the item has beenstolen
     *@param art- the art that is being stolen
    */ 
    public void steal(Art art){
        collection.add(art);
        art.setPrice("");
    }

    /*
     *Removes art from the user's inventory and resets the art's vale so that it can properly be subtracted from the user's money
     *@param art- the art that is being thrown
    */ 
    public void throwAway(Art art){
        collection.remove(art);
        art.setPrice(art.getArtPeice());
    }

    /*
     *Sets the artwork's price to 0 so that the location description does not assume that there is still an item there
     *@param art- the art that is being destroyed
    */ 
    public void destroyArt(Art art){
        art.setPrice("");
    }

    /*
     *Lists what the user can throw at the guard
     *@return i - a String showing what the user can throw
    */ 
    public String showInventory(){
        String i = "";
        for(Art a: collection){
            i += a.getArtPeice() + "\n";
        } 
        
        return i;
    }

    /*
     *Writes about the items the user has and sends it to a text file
     *@param toText - the PrintWriter
    */ 
    public void makeInventory(PrintWriter toText){
        for(Art a: collection){
            toText.println("" +a.getArtPeice());
            toText.println(""+ a.getArtDescription());
            toText.println("");
            
        }
    }

}   