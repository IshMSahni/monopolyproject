package monopoly;

import monopoly.Model.Board;
import monopoly.Model.Players;
import monopoly.Model.Property;
import monopoly.panels.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * This class is the main class for the monopoly game.
 */
public class MonopolyGUI extends GameBoardDesign {

    BoardPanel[] boardArray = new BoardPanel[]{board_1, board_2, board_3, board_4, board_5, board_6, board_7, board_8, board_9, board_10, board_11, board_12, board_13, board_14, board_15, board_16, board_17, board_18, board_19, board_20, board_21, board_22, board_23, board_24, board_25, board_26, board_27, board_28, board_29, board_30, board_31, board_32, board_33, board_34};
    BoardPanel[] ownerArray = new BoardPanel[]{own1, own2, own3, own4, own5, own6,own7, own8, own9, own10, own11, own12, own13, own14, own15, own16, own17, own18, own19, own20, own21, own22, own23, own24, own25, own26, own27, own28, own29, own30, own31, own32, own33, own34};
    Board board;
    ArrayList<Players> players = new ArrayList<>();

    Color[] colors = {
            new Color(0xF44336), new Color(0xFFA726),
            new Color(0xFFFF00), new Color(0x00E676),
            new Color(0x03A9F4), new Color(0x512DA8)
    };

    DefaultListModel<String> message_model = new DefaultListModel<>();
    DefaultListModel<String> player_model = new DefaultListModel<>();

    private int player_number;
    private int AI_number;

    MonopolyGUI self;
    AIPlayingEvent ai;

    /**
     * Create the frame.
     */
    public MonopolyGUI() {
        super();
        setTitle("Monopoly");
        setSize(865, 700);

        ToolTipManager.sharedInstance().setInitialDelay(1);

        board = new Board();
        self = this;
        player_number = -1;
        AI_number = -1;


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MonopolyGUI frame = new MonopolyGUI();
                frame.setVisible(true);
                frame.setup();
                frame.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method is called when game start
     * @return User input
     */
    private Integer ask_for_AI_number(){
        while(AI_number < 0 || AI_number > Config.MAX_PLAYERS){
            if(AI_number != -1){
                DialogProvider.getInstance()
                        .show_message_dialog(self, "Please enter a number between 0 and " + (Config.MAX_PLAYERS));
            }
            try{
                String input = DialogProvider.getInstance()
                        .show_input_dialog(self, "How many AI players will be playing Monopoly? (0-" + (Config.MAX_PLAYERS) + ")");
                if(input == null){
                    return null;
                }
                else{
                    AI_number = Integer.parseInt(input);
                }
            }
            catch(NumberFormatException e){
                DialogProvider.getInstance()
                        .show_message_dialog(self, "Please enter a number between 0 and " + (Config.MAX_PLAYERS));
            }
        }
        return AI_number;
    }

    /**
     * This method is called when game start
     * @return Uset input
     */
    private Integer ask_for_player_number(){
       int min = 0;

        if(AI_number == 0) {
            min = 2;
        }

        if(AI_number == 1){
            min = 1;
        }

        while(player_number < min || player_number > (Config.MAX_PLAYERS - AI_number)){
            if(player_number != -1){
                DialogProvider.getInstance()
                        .show_message_dialog(self, "Please enter a number between " + min + " and " + (Config.MAX_PLAYERS - AI_number));
            }
            try{
                String user_input = DialogProvider.getInstance()
                        .show_input_dialog(self, "How many human players will be playing Monopoly? (" + min + "-" + (Config.MAX_PLAYERS - AI_number) + ")");
                if(user_input == null){
                    return null;
                }
                else{
                    player_number = Integer.parseInt(user_input);
                }
            }
            catch(NumberFormatException e){
                DialogProvider.getInstance()
                                .show_message_dialog(self, "Please enter a number between " + min + " and " + (Config.MAX_PLAYERS - AI_number));
            }
        }
        return player_number;

    }

    /**
     * Require a name for the player
     * @return Player name
     */
    private String ask_for_player_name(int i){
        String player_name = "";
        while(player_name.length() == 0){
            player_name = DialogProvider.getInstance().show_input_dialog(self, "What is your name? Player " + (i + 1));
        }
        return player_name;
    }

    @Override
    public Component add(Component comp) {
        return super.add(comp);
    }

    /**
     * This method is called when add a message to the message list
     * @param message
     */
    void add_message(String message){
        if (message_model.size() >= 28){
            message_model.remove(0);
        }
        message_model.addElement(message);
    }

    /**
     * For unit test, get the message model
     * @return
     */
    public DefaultListModel<String> ut_get_message_model(){
        return message_model;
    }

    /**
     * For unit test, get the player model
     * @return
     */
    public DefaultListModel<String> ut_get_player_model(){
        return player_model;
    }

    /**
     * For unit test, setup player info
     */
    public void ut_setup_player_info(){
        for(int i = 0; i < 4; i++){
            Players players = new Players("Player_" + i, 0, 0, false);
            this.players.add(players);
        }
    }

    /**
     * Update the player info in ui
     */
    void update_player_info(){
        player_model.clear();
        for (Players p: players) {
            String info = "";
            info += "Player: ";
            info += p.getName();
            info += "    ";
            info += "Money: ";
            info += p.getMoney();
            info += "$    ";
            info += "Position: ";
            info += p.getPosition();
            player_model.addElement(info);
        }
    }

    /**
     * This method is deprecated
     * @param old_color
     * @param rate
     * @return
     */
    public Color color_changer(Color old_color, float rate){
        Integer red = (int) (old_color.getRed() * rate) % 255;
        Integer green = (int) (old_color.getGreen() * rate) % 255;
        Integer blue = (int) (old_color.getBlue() * rate) % 255;
        return old_color;
    }

    /**
     * Update info when mouse cursor is on a tile
     */
    public void update_propertyp(){
        for (int i = 0; i < 34; i++) {
            Property property = board.getProperties().get(i);
            String tooltip_info = "";
            tooltip_info += "<html>";
            tooltip_info += property.getName();

            if(property.getOwner().length() > 0){
                tooltip_info += "<br>";
                tooltip_info += "Owned by ";
                tooltip_info += property.getOwner();
            }
            tooltip_info += "</html>";

            boardArray[i].setToolTipText(tooltip_info);
            if(property.getOwner().length() > 0){
                int owner_index = -1;
                int j = 0;
                while(j < players.size()){
                    if(players.get(j).getName().equals(property.getOwner())){
                        owner_index = j;
                    }
                    j++;
                }
                if(owner_index != -1){
                    Color to_set = colors[owner_index];
                    ownerArray[i].setBackground(color_changer(to_set, 1.0f));
                }
                else{
                    ownerArray[i].setBackground(new Color(210, 208, 203));
                }
            }
            else
                ownerArray[i].setBackground(new Color(210, 208, 203));
            }
    }

    Players current_players_object = null;

    /**
     * Change current player
     * @param players
     */
    public void change_player(Players players){
        current_players_object = players;
        add_message("It is now " + current_players_object.getName() + "'s turn.");
        ai = new AIPlayingEvent(self, current_players_object);
        ai.aiTurn();

    }

    /**
     * Start the game
     */
    private void play(){
        change_player(players.get(0));
    }

    /**
     * Update colors of players
     */
    void apply_colors(){
        for(int i = 0; i < boardArray.length; i++){
            boardArray[i].clear_players();
        }

        for (Players p: players) {
            Integer position = p.getPosition();
            boardArray[position].add_player(colors[players.indexOf(p)]);
        }

        contentPane.repaint();

    }

    /**
     * Find player by name
     * @param name name
     * @return
     */
    Players find_player_by_name(String name){
        for (Players p: players) {
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
    public void remove_player(Players players, String reason){
        this.players.remove(players);
        add_message(players.getName() + " was removed from the game for " + reason);
        update_player_info();
    }

    Integer final_dice_point = 1;
    // 1. Go
    // 2. Print (No use)
    // 3. End Round
    // 4. Surrender

    /**
     * Setup game
     */
    private void setup(){
        AI_number = ask_for_AI_number();
        for (int i = 0; i < AI_number; i++) {
            players.add(new Players("AI Number " + (i + 1), Config.PLAYER_INITIAL_MONEY, 0, true));
            add_message("Added player: " + players.get(players.size() - 1).getName());
        }

        player_number = ask_for_player_number();
        for (int i = 0; i < player_number; i++) {
            players.add(new Players(ask_for_player_name(i), Config.PLAYER_INITIAL_MONEY, 0, false));
            add_message("Added player: " + players.get(players.size() - 1).getName());
        }

        update_player_info();
        message_list.setModel(message_model);
        player_list.setModel(player_model);
        for (MouseListener ml: player_list.getMouseListeners()
             ) {
            player_list.removeMouseListener(ml);
        }
        for (MouseMotionListener mml: player_list.getMouseMotionListeners()
        ) {
            player_list.removeMouseMotionListener(mml);
        }
        current_player.setText("");
        update_propertyp();
        apply_colors();
        btn_roll.addActionListener(new RollActionEvent(self));
        btn_end_round.addActionListener(new EndRoundActionEvent(self));
        btn_surrender.addActionListener(new SurrenderActionEvent(self));
    }


}
