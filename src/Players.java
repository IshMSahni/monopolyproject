import java.util.*;

/**
 * @author Aayush Mallya
 * Sets up the Player Object
 */
public class Players {



    private String name;
    private int money;
    private int position;


    /**
     * Constructor for player
     * @param name
     * @param money
     * @param position
     */
    public Players(String name, int money, int position){
        this.name = name;
        this.money = money;
        this.position = position;


    }

    /**
     * Setter for Position
     * @param pos
     */
    public void setPosition(int pos){
        this.position = pos;
    }

    /**
     * Getter for Name
     * @return
     */
    public String getName(){

        return this.name;
    }

    /**
     * Setter for Money
     * @param mon
     */
    public void setMoney(int mon){
        this.money = mon;
    }

    /**
     * Getter for Money
     * @return
     */
    public int getMoney(){

        return this.money;
    }

    /**
     * Getter for position
     * @return
     */
    public int getPosition(){
        return this.position;
    }

}
