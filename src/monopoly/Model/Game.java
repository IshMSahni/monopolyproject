package monopoly.Model;

import java.util.*;
import java.util.Scanner;

/**
 * @author Aayush Mallya
 */
public class Game  {
    private List <Players> players;
    private Board board;
    private Players p1;
    private BuyCommand command;
    private PrintCommand printCommand;
    private int numberPlayers;
    private  int com;
    private GameState state;


    public Game(){

        players = new ArrayList<>();
        board = new Board();
        com = 0;

    }

    /**
     * Adds a player object to the list of players
     * @param players
     */
    public void addPlayer(Players players){
        this.players.add(players);
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
        System.out.println("Welcome to Monopoly! How many players will be playing? (2-4 monopoly.Model.Players permitted) ");
        this.state = GameState.SET_UP;

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
            Players players = new Players(name, 500, 1);
            addPlayer(players);

        }
        System.out.println(" \n These are the player names: ");
        for(int i = 0; i < players.size(); i++)
            System.out.println(players.get(i).getName());

    }


    /**
     * Depending on the monopoly.Model.Game State it will dispatch actions
     * @param state
     */
    private void actionDispatch(GameState state){
        if(state == GameState.BUY)
            new BuyCommand(players.get(0), players.get(0).getPosition(), players.get(0).getName(), board);

        else if(state == GameState.GAMEOVER)
            System.out.println("Thanks for playing!");

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

            if (com == 1){
                state = GameState.BUY;
                actionDispatch(state);
                players.get(0).setPosition(players.get(0).getPosition()+1);
            }


            else if (com == 2) {
                for (int i = 0; i < players.size(); i++)
                    printCommand = new PrintCommand(players.get(i), board);
            } else if (com == 3) {
                //CHANGE TURN
            } else if (com == 4) {
                state = GameState.GAMEOVER;
                actionDispatch(state);
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