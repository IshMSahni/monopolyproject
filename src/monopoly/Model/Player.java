package monopoly.Model;

/**
 * @author Aayush Mallya
 * Sets up the Player Object
 */
public class Player {



    private String name;
    private int money;
    private int position;



    public Player(String name, int money, int position){
        this.name = name;
        this.money = money;
        this.position = position;

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


    public int getPosition(){
        return this.position;
    }

}
