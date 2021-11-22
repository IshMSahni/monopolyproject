package monopoly.Model;

import monopoly.Bankrupt;
import monopoly.Config;

/**
 * @author Aayush Mallya
 * Sets up the Player Object
 */
public class Players {



    private String name;
    private int money;
    private int position;
    private boolean inJail;
    private int turnsInJail;

    public Players(String name, int money, int position){
        this.name = name;
        this.money = money;
        this.position = position;
        this.inJail = false;
        this.turnsInJail = 0;
    }

    public int go(int dice_point){
        position += dice_point;
        position %= Config.BOARD_SIZE;
        return position;
    }


    public void goToJail(){
        inJail = true;
    }
    public void getOutofJail(){
        inJail = false;
    }

    public Integer getTurnsInJail(){
        return this.turnsInJail;
    }

    public void setTurnsInJail(){
        turnsInJail = 0;
    }

    public void incrementTurnsInJail(){
        this.turnsInJail += 1;
    }


    public boolean getInJail(){
        return this.inJail;
    }


    public void setPosition(int pos){
        this.position = pos;
    }

    public String getName(){

        return this.name;
    }

    public void setMoney(int mon){
        this.money = mon;
    }

    public int getMoney(){

        return this.money;
    }


    public int getPosition(){
        return this.position;
    }

}

