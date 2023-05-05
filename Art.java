/*
    Sarah Tetterton
    CPSC 1060: Rpg project
    Github Repository: Wacky-Art-Heist
    Lab Section: 3
    5/4/2023
*/
public class Art{
    private String artPeice;
    private String artDescription;
    private double artValue;

    public Art(String artPeice, String artDescription){
        this.artPeice = artPeice;
        this.artDescription = artDescription;
    }

    public void setPrice(String artPeice){
        switch(artPeice) {
            case "Bannana":
              this.artValue = 387200;
              break;
            case "Painted Trash Cube":
                this.artValue = 12000;
                break;
            case "Tree With Money Taped to it":
                this.artValue = 3500;
                break;
            case "Picture of a picture of a picture of Starry Night":
                this.artValue = 143000;
                break;
            case "Smiley face with a frown drawn on it":
                this.artValue = 90520;
                break;
            default:
                this.artValue = 0;
                break;
        }
    }

    public double getPrice(){
        return this.artValue;
    }
    
    public String getArtPeice(){
        return this.artPeice;
    }
    public String getArtDescription(){
        return this.artDescription;
    }
}