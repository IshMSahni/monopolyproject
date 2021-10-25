import java.util.*;
import java.util.Scanner;

/**
 * @author Aayush Mallya, Ishanov Sahni
 */
public class Game  {
    private List <Players> players;
    private Board board;
    private Players p1;
    private  BuyCommand command;
    private PrintCommand printCommand;
    private int numberPlayers;
    private  int com;
    private GameState state;
    private Dice dice;

    public Game(){

        players = new ArrayList<>();
        board = new Board();
        dice = new Dice();
        com = 0;
        dice = new Dice();

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
            Players player = new Players(name, 500, 0);
            addPlayer(player);

        }
        System.out.println("\nThese are the player names: ");
        for(int i = 0; i < players.size(); i++)
            System.out.println(players.get(i).getName());


    }


    /**
     * Depending on the Game State it will dispatch actions
     * @param state
     */
    private void actionDispatch(GameState state, int player){
        if(state == GameState.BUY)
            new BuyCommand(players.get(player), players.get(player).getPosition(), players.get(player).getName(), board);

        else if(state == GameState.GAMEOVER)
            System.out.println("Thanks for playing!");
    }

    /**
     * Runs the game through text-based I/O
     */
    public Integer play(){
        Scanner c = new Scanner(System.in);
        for (int i = 0; i < players.size(); i++) {
            int roll = dice.rollDice();
            System.out.println("------------------\nIt is now " + players.get(i).getName() + "'s turn.\nYou are currently on: " + board.propertyholder.get(players.get(i).getPosition()).getName());
            System.out.println("You rolled a(n) " + roll);
            players.get(i).setPosition(players.get(i).getPosition() + roll);
            if(players.get(i).getPosition() > 26)
                players.get(i).setPosition(players.get(i).getPosition() - 26);
            System.out.println("You are now on: " + board.propertyholder.get(players.get(i).getPosition()).getName());

            if(board.propertyholder.get(players.get(i).getPosition()).getOwner() != players.get(i).getName() && board.propertyholder.get(players.get(i).getPosition()).isOwned()){
               new PayRent(players.get(i), players, board, i);
            }
            while (com != 4) {
                System.out.println("\nEnter command: \n " +
                        "Press 1 to buy the property you are currently on,\n " +
                        "Press 2 to get information about your Player,\n " +
                        "Press 3 to end your turn, \n " +
                        "Press 4 to end the entire game.");
                com = Integer.parseInt(c.nextLine());
                if (com == 1) {
                    state = GameState.BUY;
                    actionDispatch(state, i);
                } else if (com == 2) {
                    printCommand = new PrintCommand(players.get(i), board);
                } else if (com == 3) {
                    com = 4;
                    break;
                } else if (com == 4) {
                    state = GameState.GAMEOVER;
                    actionDispatch(state, i);
                    return com;
                }
            }

            if (players.get(i).getMoney() < 0){
                state = GameState.BANKRUPT;
                actionDispatch(state, i);
                new Bankrupt().DeclareBankruptcy(players, i,board);
            }
            com = 0;
        }
            return com;
    }


    public static void main(String[] args) {

        Game game = new Game();
        game.setup();
        Integer quit = 0;
        while(quit != 4) {
            quit = game.play();
        }

    }

}