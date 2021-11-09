package monopoly;

import monopoly.Model.Players;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurrenderActionEvent implements ActionListener {

    private MonopolyGUI game;

    public SurrenderActionEvent(MonopolyGUI game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("Player: " + game.current_players_object.getName() + " ended Round!");

        if(game.players.size() == 1){
            JOptionPane.showMessageDialog(game.self, " Player: " + game.current_players_object.getName() + " won the game!");
            System.exit(0);
        }

        game.add_message("Player: " + game.current_players_object.getName() + " ended Round!");
        Players next_players = game.players.get((game.players.indexOf(game.current_players_object) + 1) % game.players.size());
        if(game.current_players_object.getMoney() <= 0){
            game.remove_player(game.current_players_object, "Surrender");
        }
        game.change_player(next_players);
        game.btn_roll.setEnabled(true);
    }

}
