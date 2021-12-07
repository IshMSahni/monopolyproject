package monopoly;

import monopoly.Model.Game;
import monopoly.Model.Player;
import monopoly.Model.Property;
import monopoly.event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameControl {

    Color[] colors = {
            new Color(0xF44336), new Color(0xFFA726),
            new Color(0xFFFF00), new Color(0x00E676),
            new Color(0x03A9F4), new Color(0x512DA8)
    };

    Game game;
    MonopolyGUI view;

    public GameControl(MonopolyGUI _view) {
        game = new Game();
        view = _view;
    }

    /**
     * This method is called when game start
     * @return User input
     */
    private Integer ask_for_AI_number(){
        while(game.AI_number < 0 || game.AI_number > Config.MAX_PLAYERS){
            if(game.AI_number != -1){
                view.showMessage("Please enter a number between 0 and " + (Config.MAX_PLAYERS));
            }
            try{
                Integer input = view.askInteger("How many AI players will be playing Monopoly? (0-" + (Config.MAX_PLAYERS) + ")");
                if(input == null){
                    return null;
                }
                else{
                    game.AI_number = input;
                }
            }
            catch(NumberFormatException e){
                view.showMessage("Please enter a number between 0 and " + (Config.MAX_PLAYERS));
            }
        }
        return game.AI_number;
    }


    /**
     * This method is called when game start
     * @return Uset input
     */
    private Integer ask_for_player_number(){
        int min = 0;

        if(game.AI_number == 0) {
            min = 2;
        }

        if(game.AI_number == 1){
            min = 1;
        }

        while(game.player_number < min || game.player_number > (Config.MAX_PLAYERS - game.AI_number)){
            if(game.player_number != -1){
                view.showMessage("Please enter a number between " + min + " and " + (Config.MAX_PLAYERS - game.AI_number));
            }
            try{
                Integer input = view.askInteger("How many human players will be playing Monopoly? (" + min + "-" + (Config.MAX_PLAYERS - game.AI_number) + ")");
                if(input == null){
                    return null;
                }
                else{
                    game.player_number = input;
                }
            }
            catch(NumberFormatException e){
                view.showMessage("Please enter a number between " + min + " and " + (Config.MAX_PLAYERS - game.AI_number));
            }
        }
        return game.player_number;

    }

    /**
     * Require a name for the player
     * @return Player name
     */
    private String ask_for_player_name(int i){
        String player_name = "";
        while(player_name.length() == 0){
            player_name = view.askString("What is your name? Player " + (i + 1));
            if(player_name == null){
                continue;
            }
        }
        return player_name;
    }

    /**
     * This method is called when add a message to the message list
     * @param message
     */
    public void add_message(String message){
        if (game.message_model.size() >= 28){
            game.message_model.remove(0);
        }
        game.message_model.addElement(message);
    }

    /**
     * For unit test, get the message model
     * @return
     */
    public DefaultListModel<String> ut_get_message_model(){
        return game.message_model;
    }

    /**
     * For unit test, get the player model
     * @return
     */
    public DefaultListModel<String> ut_get_player_model(){
        return game.player_model;
    }

    /**
     * For unit test, setup player info
     */
    public void ut_setup_player_info(){
        for(int i = 0; i < 4; i++){
            Player players = new Player("Player_" + i, 0, 0, false);
            game.players.add(players);
        }
    }

    /**
     * Update the player info in ui
     */
    public void update_player_info(){
        game.player_model.clear();
        for (Player p: game.players) {
            String info = "";
            info += "Player: ";
            info += p.getName();
            info += "    ";
            info += "Money: ";
            info += p.getMoney();
            info += (Config.configurable_dollar_sign + "    ");
            info += "Position: ";
            info += p.getPosition();
            game.player_model.addElement(info);
        }
    }

    public Property getProperty(int index){
        return game.getProperty(index);
    }

    public void updateProperty(){
        for (int i = 0; i < 34; i++) {
            Property property = game.getProperty(i);
            String tooltip_info = "<html>";
            //tooltip_info += "<html>";
            tooltip_info += property.getName();

            if(property.getOwner().length() > 0){
                tooltip_info += "<br>";
                tooltip_info += "Owned by ";
                tooltip_info += property.getOwner();
            }
            tooltip_info += "</html>";
            Color new_color;

            if(property.getOwner().length() > 0){
                int owner_index = -1;
                int j = 0;
                while(j < game.players.size()){
                    if(game.players.get(j).getName().equals(property.getOwner())){
                        owner_index = j;
                    }
                    j++;
                }
                if(owner_index != -1){
                    new_color = colors[owner_index];
                }
                else{
                    new_color = (new Color(210, 208, 203));
                }
            }
            else{
                new_color = (new Color(210, 208, 203));
            }

            view.setupProperty(i, tooltip_info, new_color);
        }
    }

    AIPlayingEvent ai;

    /**
     * Change current player
     * @param players
     */
    public void change_player(Player players){
        game.current_players_object = players;
        add_message("It is now " + game.current_players_object.getName() + "'s turn.");
        ai = new AIPlayingEvent(this, game.current_players_object);
        ai.aiTurn();
    }

    public void next_player(){
        Player current_player = game.current_players_object;
        int index = game.players.indexOf(current_player);
        if(index == game.players.size() - 1){
            index = 0;
        }
        else{
            index++;
        }
        change_player(game.players.get(index));
        view.setup_btn_end_round(false);

    }

    /**
     * Start the game
     */
    public void play(){
        change_player(game.players.get(0));
    }

    /**
     * Update colors of players
     */
    public void apply_colors(){
        for(int i = 0; i < view.boardArray.length; i++){
            view.boardArray[i].clear_players();
        }

        for (Player p: game.players) {
            Integer position = p.getPosition();
            view.boardArray[position].add_player(colors[game.players.indexOf(p)]);
        }

        view.contentPane.repaint();

    }

    /**
     * Find player by name
     * @param name name
     * @return
     */
    public Player find_player_by_name(String name){
        for (Player p: game.players) {
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Remove player from game
     * @param players player to remove
     * @param reason reason
     */
    public void remove_player(Player players, String reason){
        game.players.remove(players);
        add_message(players.getName() + " was removed from the game for " + reason);
        update_player_info();
    }

    public Integer final_dice_point = 1;
    // 1. Go
    // 2. Print (No use)
    // 3. End Round
    // 4. Surrender

    public void setup() {
        game.AI_number = ask_for_AI_number();
        if(game.AI_number == null){
            view.showMessage("AI number is needed. Game will be end.");
            System.exit(0);
        }
        for (int i = 0; i < game.AI_number; i++) {
            Player ai_player = new Player("AI Number " + (i + 1), Config.PLAYER_INITIAL_MONEY, 0, true);
            game.players.add(ai_player);
            // ai_players.add(ai_player);
            add_message("Added player: " + game.players.get(game.players.size() - 1).getName());
        }

        game.player_number = ask_for_player_number();

        if(game.player_number == null){
            view.showMessage("AI number is needed. Game will be end.");
            System.exit(0);
        }

        for (int i = 0; i < game.player_number; i++) {
            game.players.add(new Player(ask_for_player_name(i), Config.PLAYER_INITIAL_MONEY, 0, false));
            add_message("Added player: " + game.players.get(game.players.size() - 1).getName());
        }

        update_player_info();
        view.message_list.setModel(game.message_model);
        view.player_list.setModel(game.player_model);


        for (MouseListener ml: view.player_list.getMouseListeners()
        ) {
            view.player_list.removeMouseListener(ml);
        }
        for (MouseMotionListener mml: view.player_list.getMouseMotionListeners()
        ) {
            view.player_list.removeMouseMotionListener(mml);
        }
        view.current_player.setText("");
        updateProperty();
        apply_colors();
        view.btn_roll.addActionListener(new RollActionEvent(this));
        view.btn_end_round.addActionListener(new EndRoundActionEvent(this));
        view.btn_surrender.addActionListener(new SurrenderActionEvent(this));
        view.btn_load.addActionListener(new LoadEvent(this));
        view.btn_save.addActionListener(new SaveEvent(this));


    }

    public void loadGame(){
        String file = view.askFile();
        game.message_model.clear();
        game.player_model.clear();
        if(file != null){
            try{
                byte[] file_bytes = Files.readAllBytes(Paths.get(file));
                Game new_game = (Game)Serialization.read_base64(new String(file_bytes));
                game = new_game;
                updateProperty();
                update_player_info();
                getView().btn_roll.setEnabled(true);
            }
            catch (Exception e){
                e.printStackTrace(System.err);
            }
        }

        view.message_list.setModel(game.message_model);
        view.player_list.setModel(game.player_model);

    }

    public Game getGameModel() {
        return game;
    }

    public MonopolyGUI getView() {
        return view;
    }





}
