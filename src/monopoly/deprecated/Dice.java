package monopoly.deprecated;

/**
 * @author Gang Han
 * Rolls the dice, generating 2 random numbers
 */

public class Dice {

    private int dice1;
    private int dice2;

    public Dice(){
    }

    /**
     * Roll the two dice, return the combined value of both dice
     * @return
     */
    public int rollDice(){
        dice1 =(int)(Math.random()*6+1);
        dice2 =(int)(Math.random()*6+1);

        return dice1+dice2;
    }

}
