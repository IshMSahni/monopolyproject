import java.util.*;
import java.util.Scanner;

/**
 * @author Aayush Mallya
 */
public class Game  {
    private List <Players> players;
    private Board board;
    private Players p1;
    private  BuyCommand command;
    private PrintCommand printCommand;
    private int numberPlayers;
    private  int com;


    public Game(){

        players = new ArrayList<>();
        board = new Board();
        com = 0;

    }

    /**
     * Adds a player object to the list of players
     * @param player
     */
    public void addPlayer(Players player){
        players.add(player);
    }

    public Players removePlayer(int index){

        if(index >= 0 && index < players.size()){
            return players.remove(index);
        }
        return null;
    }

    /**
     * Gets the number of players/names of players
     *
     */
    public void setup(){

        Scanner c = new Scanner(System.in);
        System.out.println("Welcome to Monopoly! How many players will be playing? (2-4 Players permitted) ");

        while (numberPlayers < 2 || numberPlayers > 4) { //ensures the user inputs number of players between 2-6

            try {
                numberPlayers = Integer.parseInt(c.nextLine());

                if (numberPlayers < 2 || numberPlayers > 4)
                    throw new IllegalArgumentException();

            } catch (NumberFormatException e) {
                System.out.println("You must enter an integer value for number of players!");
            }catch (IllegalArgumentException e){
                System.out.println("You are only allowed to have 2-4 players.");
            }
        }

        System.out.println("Enter the names of each player: ");

        for(int i = 0; i < numberPlayers; i++){
            System.out.print ("Player " + (i + 1) + ": " );
            String name = c.nextLine();
            Players player = new Players(name, 500, 0);
            addPlayer(player);

        }
        System.out.println(" \n These are the player names: ");
        for(int i = 0; i < players.size(); i++)
            System.out.println(players.get(i).getName());


    }

    /**
     * Calls the buy command
     * @param players
     * @param position
     * @param playerName
     */
    private void buyProp(Players players,int position, String playerName){
        command = new BuyCommand( players,position, playerName);
    }

    /**
     * Calls the print player state command
     * @param players
     */
    private void printState(Players players){
        printCommand = new PrintCommand(players);
    }

    /**
     * CURRENTLY HARDCODED FOR TESTING
     * "get(index)" for command 1--> index will be based on current turn; not a static number
     */
    public void play(){
        Scanner c = new Scanner(System.in);

        while(com != 4) {
            System.out.println("Enter command: (1,2,3,4)");
            com = Integer.parseInt(c.nextLine());

            if (com == 1)
                buyProp(players.get(0), players.get(0).getPosition(), players.get(0).getName());

            else if (com == 2) {
                for (int i = 0; i < players.size(); i++)
                    printState(players.get(i));
            } else if (com == 3) {
                //CHANGE TURN
            } else if (com == 4) {
                System.out.println("Thanks for playing!");
                break;
            }
        }

    }







    public static void main(String[] args) {

        Game game = new Game();
        game.setup();
        game.play();

    }

}
