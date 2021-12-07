package monopoly.deprecated;

import monopoly.Model.Player;
import monopoly.Model.Property;

/**
 * @author Aayush Mallya
 * Code for the Buy Command
 */
public class BuyCommand {
    private final Player players;
    private Property property;
    private final monopoly.Model.Board board;

    public BuyCommand(Player players, int position, String name, monopoly.Model.Board board){
        this.players = players;
        this.board = board;

        buyConditions(position, name);

    }

    public void buyConditions(int position, String playerName){

        if(board.propertyholder.get(position).isOwned())
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
