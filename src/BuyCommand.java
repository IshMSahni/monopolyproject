import java.util.*;

/**
 * @author Aayush Mallya
 * Code for the Buy Command
 */
public class BuyCommand {
    private Players players;
    private Property property;
    private Board board;

    public BuyCommand(Players players, int position, String name, Board board){
        this.players = players;
        this.board = board;

        buyConditions(position, name);

    }

    public void buyConditions(int position, String playerName){

        if(!board.propertyholder.get(position).isSpecial())
            System.out.println("Sorry this is a government property and is not for sale.");
        else if(board.propertyholder.get(position).isOwned())
            System.out.println("Sorry, this property is already owned");

        else if (players.getMoney() < board.propertyholder.get(position).getCost()){
            System.out.println("Sorry, you do not have enough money to purchase this property!");
        }

        else{
            //board.newOwner(position, playerName);//propertyholder.get(position).nOwner(position, playerName);
            board.propertyholder.get(position).setOwner(playerName);
            players.setMoney(players.getMoney() - board.propertyholder.get(position).getCost());
            System.out.println(board.propertyholder.get(position).getName() + " is now owned by: " + board.propertyholder.get(position).getOwner());
        }

    }



}
