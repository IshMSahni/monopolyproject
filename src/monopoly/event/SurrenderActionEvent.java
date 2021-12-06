package monopoly.event;

import monopoly.GameControl;
import monopoly.Model.Player;
import monopoly.MonopolyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurrenderActionEvent implements ActionListener {

    private final GameControl game;

    public SurrenderActionEvent(GameControl game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("Player: " + game.getGameModel().current_players_object.getName() + " ended Round!");

        game.remove_player(game.getGameModel().current_players_object, "Surrender");

        if(game.getGameModel().players.size() == 1){
            JOptionPane.showMessageDialog(game.getView(), " Player: " + game.getGameModel().current_players_object.getName() + " won the game!");
            System.exit(0);
        }

        game.add_message("Player: " + game.getGameModel().current_players_object.getName() + " ended Round!");
        Player next_players = game.getGameModel().players.get((game.getGameModel().players.indexOf(game.getGameModel().current_players_object) + 1) % game.getGameModel().players.size());
        if(game.getGameModel().current_players_object.getMoney() <= 0){
            game.remove_player(game.getGameModel().current_players_object, "Surrender");
        }
        game.change_player(next_players);
        game.getView().btn_roll.setEnabled(true);
    }

}
