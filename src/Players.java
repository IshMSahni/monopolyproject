import java.util.*;

/**
 * Player Class contains information related to the player.
 */
public class Players {
    private List<Property> properties = new ArrayList<>();

    private String name;
    private int money;
    private int position;



    public Players(String name, int money, List<Property> properties, int position){
        this.name = name;
        this.money = money;
        this.position = position;
        this.properties = properties;

    }

    public void addProperty(Players player, Property property ){

        player.properties.add(property);
    }

    public void setPosition(int pos){
        this.position = pos;
    }

    public String getName(){

        return this.name;
    }

    public int getMoney(){

        return this.money;
    }

    public List<Property> getProperties(){

        return this.properties;
    }

    public int getPosition(){
        return this.position;
    }

}
