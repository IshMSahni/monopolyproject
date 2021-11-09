package monopoly;

import monopoly.Model.Board;
import monopoly.Model.Players;

import java.util.List;

public class Bankrupt {

    /**
     * Declare a player's bankruptcy, remove his properties
     * @param players
     * @param i
     * @param board
     */
    public void DeclareBankruptcy(List<Players> players, Integer i, Board board) {

        System.out.println("Oh no" + players.get(i).getName() + "went bankrupt \n" +
                "All of their properties are now available for purchase when landed on!");
        for (int j = 0; j < board.getProperties().size(); j++) {
            if (board.propertyholder.get(j).getOwner().equalsIgnoreCase(players.get(i).getName())) {
                board.propertyholder.get(j).setOwner("");
                board.propertyholder.get(j).removeOwner();
            }
        }
    }
}