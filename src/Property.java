import java.util.*;

/**
 *
 */
public class Property {

    private List<Property> properties = new ArrayList<>();
    private String name;
    private int cost;
    private boolean isOwned;
    private boolean isSpecial;
    private int position;

    private int numHouses;
    private int numHotels;

    public Property(String name, int cost, boolean isOwned, int position, boolean isSpecial){
        this.name = name;
        this.cost = cost;
        this.isOwned = isOwned;
        this.position = position;
        this.isSpecial = isSpecial;
    }

    public String getName(){
        return name;
    }

    public int getCost(){
        return cost;
    }

    public boolean isOwned(){
        return isOwned;
    }

    public int getPosition(){
        return position;
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

    private int calcRent(Property property){
       // return (int)((0.1 * property.getCost()) + (0.05 * property.getNumHouses()) + (0.1 * property.getNumHotels()));
        return (int)(0.1 * property.getCost());
    }














}
