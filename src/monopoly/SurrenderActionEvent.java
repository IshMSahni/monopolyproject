package monopoly;

import monopoly.Model.Player;
import monopoly.MonopolyGUI;

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
        System.err.println("Player: " + game.current_player_object.getName() + " ended Round!");

        if(game.players.size() == 1){
            JOptionPane.showMessageDialog(game.self, " Player: " + game.current_player_object.getName() + " won the game!");
            System.exit(0);
        }

        game.add_message("Player: " + game.current_player_object.getName() + " ended Round!");
        Player next_player = game.players.get((game.players.indexOf(game.current_player_object) + 1) % game.players.size());
        if(game.current_player_object.getMoney() <= 0){
            game.remove_player(game.current_player_object, "Surrender");
        }
        game.change_player(next_player);
        game.btn_roll.setEnabled(true);
    }

}
