import java.util.*;
import java.util.Scanner;

public class Game  {
    private List <Players> players;
    private int numberPlayers;
    private GameState state;


    public Game(){

        players = new ArrayList<>();
    }

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
     */
    public void setup(){
        Scanner c = new Scanner(System.in);
        System.out.println("Welcome to Monopoly! How many players will be playing? (2-4 Players permitted) ");
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
            Players player = new Players(name, 500, null, 0);
            addPlayer(player);

        }
        System.out.println(" \n These are the player names: ");
        for(int i = 0; i < players.size(); i++)
            System.out.println(players.get(i).getName());


    }

    /**
     * The game will be played from here
     */
    public void play(){

    }







    public static void main(String[] args) {

        Game game = new Game();
        game.setup();

    }

}
