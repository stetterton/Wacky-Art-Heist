/*
    Sarah Tetterton
    CPSC 1060: Rpg project
    Github Repository: Wacky-Art-Heist
    Lab Section: 3
    5/4/2023
*/
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
public class ArtHeist {
    /*
     *Lists what the user is able to do when they encounter a guard
     *@return op- A string that lists the options
    */ 
    public static String guardOptions(){
        String op = "";
        op += "Run\n";
        op += "Throw art";
        return op;
    }
    /*
     *Lists what the user is able to do when they encounter a guard
     *@param money - the amount of money the user obtained
     *@return finalMessage - A string tells the user how sucsessfull they were
    */ 
    public static String winCheck(double money){
        String finalMessage = "";

        if(money == 0){
            finalMessage = "You didn't gain a single dollar! Why did you go here!?";
            return finalMessage;
        }
        else if(money <= 30000) {
            finalMessage = "Well at least you got something, but you could have gotten more.";
            return finalMessage;
        }
        else if(money <= 150000) {
            finalMessage = "Congratulations, you made over 100,000 dollars selling crappy art!";
            return finalMessage;
        }
        else if(money <= 250000){
            finalMessage = "Congratulations, you made over 200,000 dollars selling crappy art!";
            return finalMessage;
        }
        else if(money == 387200) {
            finalMessage = "Your life savings came from a bannana. How does that make you feel?";
            return finalMessage;
        }
        else {
            finalMessage = "Wow, you stole over 400,000 dollars worth of 'prized' items from the museum. Now have fun avoiding the law in your limo.";
            return finalMessage;
        }
    }
    /*
     *Gives message based on how expensive the art is
     *@param price - the value of the thrown artwork
     *@return message- A string that hints at the value of the thrown artwork
    */ 
    public static String throwCheck(double price){
        String message = "";

        if(price <= 12000){
            message = "The guard was a little bummed about the artwork's destruction.";
            return message;
        }
        else if(price <= 50000){
            message = "The guard really didn't like watching the artwork splat on their face.";
            return message;
        }
        else if(price <= 150000){
            message = "The guard paniked at the destruction of the valuable artwork.";
            return message;
        }
        else {
            message = "The guard screams realizing that over 200000 dollars were thoughtlessly destroyed.";
            return message;
        }
    }
    public static void main(String[] args) {
        //Map and Inventory are initialized at the beginning of the game and then filled with locations and art respectivly 
        Map museum = new Map();
        Inventory in = new Inventory(); 
        int hp = 20;
        //Is randomly generated. If it is greater than or equal to 10, then the user will encounter a guard.
        int detection = 0;
        //Varible risk becomes bigger with every stolen object and gets added to detection after it has been generated by the random object
        int risk = 0;
        // Varible flee is randomly generated to see if the user can run from a guard
        int flee = 0;
        double money = 0.0;
        boolean found = false;
        //Different input varibes are used for different loops to prevent the user from accidently breaking out of the main one
        String gInput = "";
        String eInput = "";
        String iInput = "";
        
        Scanner scnr = new Scanner(System.in);
        Random randy = new Random();
        
        //Art u and f are meant to be blank varibles to fill cLoc and pLoc
        Art u = new Art("", "");
        Art f = new Art("", "");
        
        //cLoc and pLoc keep track of where the user is
        Location cLoc = new Location("", "", u);
        Location pLoc = new Location("", "", f);

        Art bannana = new Art("Bannana", "It's a bannana that was taped to a wall. Smells strange.");
        bannana.setPrice("Bannana");
        Location postMod = new Location("Post Modern", "This room is filled to the brim with unique masterpeices.", bannana);
        postMod.addOtherRoom("Environmental");
        postMod.addOtherRoom("Insights");
        postMod.addOtherRoom("Humanity");
        postMod.setRisk(4);
        museum.addLocation(postMod);

        Art trashCube = new Art("Painted Trash Cube","It's a pile of garbage that was dipped in paint. Extra sticky.");
        trashCube.setPrice("Painted Trash Cube");
        Location environment = new Location("Environmental", "An avant garde approach to the ever-present issues our globe faces.", trashCube);
        environment.setRisk(5);
        environment.addOtherRoom("Memorial");
        environment.addOtherRoom("Post Modern");
        museum.addLocation(environment);

        Art moneyTree = new Art("Tree With Money Taped to it","A small plastic tree with several dollar bills taped to it. What could it possibly mean?");
        moneyTree.setPrice("Tree With Money Taped to it");
        Location insight = new Location("Insights", "Reflect on society's greatest questions through these groundbreaking works.", moneyTree);
        insight.setRisk(1);
        insight.addOtherRoom("Enterence");
        museum.addLocation(insight);

        Art photoOfPainting = new Art("Picture of a picture of a picture of Starry Night", "This has to break some copyright law.");
        photoOfPainting.setPrice("Picture of a picture of a picture of Starry Night");
        Location memorial = new Location("Memorial", "Take some time to appreciate the greats through our truly special lens.", photoOfPainting);
        memorial.setRisk(2);
        memorial.addOtherRoom("Environmental");
        museum.addLocation(memorial);

        Art smiley = new Art("Smiley face with a frown drawn on it", "Ah yes, the deepest reflection of the human condition");
        smiley.setPrice("Smiley face with a frown drawn on it");
        Location human = new Location("Humanity", "Here we can look at the core aspect of our society, the human.", smiley);
        human.setRisk(3);
        human.addOtherRoom("Insights");
        human.addOtherRoom("Post Modern");
        museum.addLocation(human);
        

        Location entrence = new Location("Enterence", "", u);
        entrence.setRisk(0);
        entrence.addOtherRoom("Memorial");
        entrence.addOtherRoom("Insights");
        museum.addLocation(entrence);

        cLoc = entrence;
        pLoc = cLoc;
        System.out.println("You have entered the Unique Brilliance Art Museum.");
        System.out.println("The art here is indeed quite 'unique' so it will be difficult to sniff out the valuable ones.");
        System.out.println("It's your job to attempt to get the ones that will make you rich.");
        System.out.println("Now get going!");
        System.out.println("What's your first move?");
        
        System.out.println("" +entrence.showOptions());

        String input = scnr.nextLine();
        //Main while loop runs on a condition that will never be met so that the user will only end the game via win or lose conditions
        while (input.equalsIgnoreCase("WowWee%*%^&%") == false){
            
            ArrayList<String> rooms = new ArrayList<String>();
            rooms = cLoc.getOtherRooms();
            //Keeps running until cLoc is different than pLoc
            while(cLoc == pLoc){
                //Iterates through rooms
                for(String o: rooms){
                    if(input.equalsIgnoreCase(o)){
                        cLoc = museum.getLocation(o);
                        break;
                    }
                }
                 
                if(cLoc == pLoc && input.equalsIgnoreCase("steal") == false && input.equalsIgnoreCase("Inventory") == false){
                    //The program asks for the user to try again when there is an invalid input
                    System.out.println("You can't do that.");
                    System.out.println("Try doing something else");
                    input = scnr.nextLine();
                }
                else if(input.equalsIgnoreCase("steal")){
                    money += cLoc.getArt().getPrice();
                    risk += cLoc.getRisk();
                    
                    //The user gets the item added to their inventory via the steal method
                    in.steal(cLoc.getArt());
                    System.out.println("What are you going to do now?");
                    System.out.println("" +cLoc.showOptions());
                    input = scnr.nextLine();
                }
                else if(input.equalsIgnoreCase("Inventory")){

                    //Creates a file that will have the user's item information
                    String backPack = String.format("Inventory.txt");
                    FileOutputStream fileStream = null;
                    try {
                        fileStream = new FileOutputStream(backPack);
                    } catch (FileNotFoundException e) {
                        System.out.println("Oh no! Your " + backPack + " does not exitst!");
                    }
                    PrintWriter toText = new PrintWriter(fileStream);
                    System.out.println("You look into your back pack");

                    in.makeInventory(toText);//the printwriter is given to makeInventory
                    toText.close();

                    System.out.println("What are you going to do now?");
                    System.out.println("" +cLoc.showOptions());
                    input = scnr.nextLine();
                    
                    
                }
                
            }
            
            
            detection = randy.nextInt(10) + risk;
            System.out.println(""+detection);
            if(detection >= 10){
                //found is set to true so the guard while loop starts
                found = true;
                System.out.println("Oh no! a guard caught you! What will you do?!");
                while(found){
                    System.out.println(""+guardOptions());
                    gInput = scnr.nextLine();
                    if (gInput.equalsIgnoreCase("Run")){
                        flee = randy.nextInt(10);
                        if (flee >= 7){
                            System.out.println("You got away");
                            risk -= 3;
                            found = false;
                            break;
                        }
                        else {
                            hp -= 10;
                            System.out.println("You tripped and could not run away");
                            if (hp <= 0) {
                                //The game ends if the user fails to get away from the guard
                                System.out.println("Woops, the guard caught you and now you're in prison.");
                                System.out.println("Game over :(");
                                System.exit(0);
                            }
                        }
                    }
                    else if (gInput.equalsIgnoreCase("Throw Art")){
                        //Varible collection is made so that the program can properly loop through options
                        ArrayList<Art> collection = new ArrayList<Art>();
                        collection = in.getCollection();
                        System.out.println("What are you going to throw at the guard?");
                        for(Art a: collection){
                            System.out.println("" +a.getArtPeice());
                        }
                        gInput = scnr.nextLine();
                        for(Art a: collection){
                            if(gInput.equalsIgnoreCase(a.getArtPeice())){
                                System.out.println("You threw the " +a.getArtPeice()+ "at the guard!");
                                in.throwAway(a);
                                System.out.println("" + throwCheck(a.getPrice()) + " You were able to get away because of this.");
                                money -= a.getPrice();
                                in.destroyArt(a);
                                found = false;
                                break;
                            }
                            else {
                                System.out.println("You don't have that.");
                                gInput = scnr.nextLine();
                            }
                            
                        }
                        
                        
                    }

                }
            }
            if (cLoc == entrence) {
                //Saying yes ends the game
                System.out.println(""+cLoc.showEnterence());
                eInput = scnr.nextLine();
                while(eInput.equalsIgnoreCase("yes") == false && eInput.equalsIgnoreCase("no") == false){
                    System.out.println("Its a yes or no question");
                    eInput = scnr.nextLine();
                }
                if(eInput.equalsIgnoreCase("yes")){
                    System.out.println("" + winCheck(money));
                    System.exit(0);
                }
                else if (eInput.equalsIgnoreCase("no")){
                    pLoc = cLoc;
                    System.out.println("What are you going to do now?");
                    System.out.println("" +cLoc.showOptions());
                    input = scnr.nextLine();
                    
                }
                
            }
            if (cLoc != entrence){
                System.out.println(""+cLoc.showRoomInfo());

            
                pLoc = cLoc;
            
            
                System.out.println("What are you going to do now?");
                System.out.println("" +cLoc.showOptions());
                input = scnr.nextLine();
            }
            
        }






        
    }
}