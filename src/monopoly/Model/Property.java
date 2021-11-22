package monopoly.Model;

import java.util.HashMap;

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
    private boolean isSpecialBuyable;
    private int numHouses;
    private int numHotels;

    public Property(String name, int cost, boolean isOwned, int position, boolean isSpecial, String owner, boolean isSpecialBuyable){
        this.name = name;
        this.cost = cost;
        this.isOwned = isOwned;
        this.position = position;
        this.isSpecial = isSpecial;
        this.owner = owner;
        this.isSpecialBuyable = isSpecialBuyable;
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

    public int getPosition(){
        return this.position;
    }

    public String getPlayer(){
        return  this.owner;
    }

    public void setOwner(String newOwner){
        this.owner = newOwner;
        isOwned = true;
    }

    public boolean isSpecial(){
        return this.isSpecial;
    }
    public boolean isSpecialBuyable(){
        return this.isSpecialBuyable;
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

    public int getRailRoadRent(int cost, Board board, String owner){
        int count = 0;
        // return (int)((0.1 * property.getCost()) + (0.05 * property.getNumHouses()) + (0.1 * property.getNumHotels()));
        if (board.getProperty(4).getOwner().equals(owner)){
            count++;
        }
        if (board.getProperty(13).getOwner().equals(owner)){
            count++;
        }
        if (board.getProperty(21).getOwner().equals(owner)){
            count++;
        }
        if (board.getProperty(30).getOwner().equals(owner)){
            count++;
        }
        return (int)(0.1 * cost * count);
    }

    public int getUtilityRent(Board board, Integer roll, String owner){

        if (board.getProperty(10).getOwner().equals(owner) && board.getProperty(24).getOwner().equals(owner)){
            return roll/2 * 10;
        }
        else{
            return roll/2 * 4;
        }
    }




    public void removeOwner() {
        this.owner = "";
        this.isOwned = false;
    }
}
