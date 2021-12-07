package monopoly.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Aayush Mallya
 * Information pertaining to the player state
 */
public class Property implements Serializable {

    private Board board;

    private String owner;
    private final String name;
    private final int cost;
    private boolean isOwned;
    private final boolean isSpecial;
    private final int position;
    private final boolean isSpecialBuyable;
    private int numHouses;
    private int numHotels;
    private final int groupNum;

    private final int housePrice;

    // static ArrayList<Property> properties = new ArrayList<Property>();
    //static String json_text = "";
    //static JSONArray jsonArray = new JSONArray();


    public Property(String name, int cost, boolean isOwned, int position, boolean isSpecial, String owner, boolean isSpecialBuyable, int groupNum, int housePrice){
        this.name = name;
        this.cost = cost;
        this.isOwned = isOwned;
        this.position = position;
        this.isSpecial = isSpecial;
        this.owner = owner;
        this.isSpecialBuyable = isSpecialBuyable;
        this.groupNum = groupNum;
        this.housePrice = housePrice;

        //properties.add(this);

        if (false) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("cost", cost);
            jsonObject.put("isOwned", isOwned);
            jsonObject.put("position", position);
            jsonObject.put("isSpecial", isSpecial);
            jsonObject.put("owner", owner);
            jsonObject.put("isSpecialBuyable", isSpecialBuyable);
            jsonObject.put("groupNum", groupNum);
            jsonObject.put("housePrice", housePrice);
            //jsonArray.put(jsonObject);
        }

        //json_text = jsonArray.toString();

        // data = new String(Files.readAllBytes(Paths.get("BoardConfig.json")));

        try{
            //Files.write(Paths.get("BoardConfig.json"), json_text.getBytes());
        }
        catch (Exception e){
            //e.printStackTrace(System.err);
        }

    }

    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.cost;
    }

    public int getHousePrice(){
        return this.housePrice;
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

    public void addHouse(int num){
        this.numHouses += num;
    }

    public int getNumHouses(){
        return numHouses;
    }

    public void addHotel(int n){
        this.numHotels += n;
    }

    public void clearNumHouse(){
        this.numHouses = 0;
    }

    public int getNumHotels(){
        return numHotels;
    }

    public int getGroupNum(){
        return groupNum;
    }

    public int getRent(int cost){
        return (int)((0.1 * this.cost) + (0.05 * this.numHouses * housePrice) + (0.1 * this.numHotels * housePrice));
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
