package monopoly.Model;

import monopoly.Config;

/**
 * @author Aayush Mallya
 * Sets up the Player Object
 */
public class Players {



    private String name;
    private int money;
    private int position;
    private boolean isAI;



    public Players(String name, int money, int position, boolean isAI){
        this.name = name;
        this.money = money;
        this.position = position;
        this.isAI = isAI;

    }

    public int go(int dice_point){
        position += dice_point;
        position %= Config.BOARD_SIZE;
        return position;
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

    public boolean checkAI(){
        return this.isAI;
    }

    public int getPosition(){
        return this.position;
    }

}
