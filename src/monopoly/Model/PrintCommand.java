package monopoly.Model;

/**
 * @author Aayush Mallya
 * Code for printing the current player state
 */
public class PrintCommand {
    private Player players;
    private Property property;
    private Board board;

    public PrintCommand(Player players, Board board){
        this.players = players;
        this.board = board;
        printState(players);
    }

    public void printState(Player players){
        System.out.println("\nPlayer Name: " + players.getName() + "\nMoney: " + players.getMoney() + "\nPosition: " + players.getPosition());
        System.out.println("Properties Owned: ");
        for(int i = 0; i < board.getProperties().size(); i++){
            if(board.propertyholder.get(i).getOwner().equalsIgnoreCase(players.getName()))
                System.out.println(board.propertyholder.get(i).getName()+ ",");

        }

    }
}
