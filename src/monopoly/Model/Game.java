package monopoly.Model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    public Board board;
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Player> ai_players = new ArrayList<>();

    public DefaultListModel<String> message_model = new DefaultListModel<>();
    public DefaultListModel<String> player_model = new DefaultListModel<>();

    public Player current_players_object;

    public Integer player_number;
    public Integer AI_number;

    public Game() {
        board = new Board();
        player_number = -1;
        AI_number = -1;

    }

    public Board getBoard(){ return board; }

    public Property getProperty(int i){ return board.getProperty(i); }



}
