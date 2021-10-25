import java.util.*;

/**
 * @author Aayush Mallya
 * Information pertaining to the player state
 */
public class Property {

    private Board board;

    private String owner;
    private String name;
    private int cost;
    private boolean isOwned;
    private boolean isSpecial;
    private int position;

    private int numHouses;
    private int numHotels;

    public Property(String name, int cost, boolean isOwned, int position, boolean isSpecial, String owner){
        this.name = name;
        this.cost = cost;
        this.isOwned = isOwned;
        this.position = position;
        this.isSpecial = isSpecial;
        this.owner = owner;
    }

    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.cost;
    }

    public boolean isOwned(){
        return this.isOwned;
    }

    public void removeOwner(){
        this.isOwned = false;
    }

    public int getPosition(){
        return this.position;
    }

    public String getPlayer(){
        return  this.owner;
    }

    public void setOwner(String newOwner){
        this.owner = newOwner;
        this.isOwned = true;
    }

    public String getOwner(){
        return  this.owner;
    }

  /*  private void addHouse(int num){
        this.numHouses += num;
    }

    public int getNumHouses(){
        return numHouses;
    }

    private void addHotel(int n){
        this.numHotels += n;
    }

    public int getNumHotels(){
        return numHotels;
    }*/

    public int getRent(int cost){
       // return (int)((0.1 * property.getCost()) + (0.05 * property.getNumHouses()) + (0.1 * property.getNumHotels()));
        return (int)(0.1 * cost);
    }














}
