package monopoly;

import monopoly.Model.Board;
import monopoly.Model.Player;
import monopoly.Model.Property;
import monopoly.event.AIPlayingEvent;
import monopoly.event.EndRoundActionEvent;
import monopoly.event.RollActionEvent;
import monopoly.event.SurrenderActionEvent;
import monopoly.panels.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is the main class for the monopoly game.
 */
public class MonopolyGUI extends GameBoardDesign {

    BoardPanel[] boardArray = new BoardPanel[]{board_1, board_2, board_3, board_4, board_5, board_6, board_7, board_8, board_9, board_10, board_11, board_12, board_13, board_14, board_15, board_16, board_17, board_18, board_19, board_20, board_21, board_22, board_23, board_24, board_25, board_26, board_27, board_28, board_29, board_30, board_31, board_32, board_33, board_34};
    BoardPanel[] ownerArray = new BoardPanel[]{own1, own2, own3, own4, own5, own6,own7, own8, own9, own10, own11, own12, own13, own14, own15, own16, own17, own18, own19, own20, own21, own22, own23, own24, own25, own26, own27, own28, own29, own30, own31, own32, own33, own34};

    MonopolyGUI self;
    GameControl game_control;


    /**
     * Create the frame.
     */
    public MonopolyGUI() {
        super();
        setTitle("Monopoly");
        setSize(865, 700);

        ToolTipManager.sharedInstance().setInitialDelay(1);

        self = this;

        game_control = new GameControl(this);
        game_control.setup();
        game_control.play();

        btn_end_round.setVisible(false);

    }

    /**
     * Launch the application.
     */
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MonopolyGUI frame = new MonopolyGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }*/

    public Integer askInteger(String message) {
        String input = DialogProvider.getInstance().show_input_dialog(self, message);
        Integer result = null;
        try {
            result = Integer.parseInt(input);
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }
        return result;
    }

    public String askString(String message){
        return DialogProvider.getInstance().show_input_dialog(self, message);
    }

    public void showMessage(String message){
            DialogProvider.getInstance().show_message_dialog(self, message);
    }

    public void setupProperty(int index, String tooltip, Color color){
        boardArray[index].setToolTipText(tooltip);
        ownerArray[index].setBackground(color);
    }

    public String askFile() { return DialogProvider.getInstance().show_file_dialog(self, "."); }

    public void setup_btn_end_round(Boolean is_enabled){
        btn_end_round.setEnabled(is_enabled);
    }













    ArrayList<Player> players = new ArrayList<>();
    DefaultListModel<String> message_model = new DefaultListModel<>();
    DefaultListModel<String> player_model = new DefaultListModel<>();

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
            Player players = new Player("Player_" + i, 0, 0, false);
            this.players.add(players);
        }
    }

}
