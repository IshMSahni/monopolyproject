package monopoly.event;

import monopoly.GameControl;
import monopoly.Model.Player;
import monopoly.MonopolyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndRoundActionEvent implements ActionListener {
    private final GameControl game;

    public EndRoundActionEvent(GameControl game){
        this.game = game;
    }

    public void endTurn(){
        System.err.println("Player: " + game.getGameModel().current_players_object.getName() + " ended Round!");

        if(game.getGameModel().players.size() == 1){
            JOptionPane.showMessageDialog(game.getView(), "Player: " + game.getGameModel().current_players_object.getName() + " won the game!");
            System.exit(0);
        }

        game.add_message("Player: " + game.getGameModel().current_players_object.getName() + " ended Round!");
        Player next_players = game.getGameModel().players.get((game.getGameModel().players.indexOf(game.getGameModel().current_players_object) + 1) % game.getGameModel().players.size());
        if(game.getGameModel().current_players_object.getMoney() <= 0){
            game.remove_player(game.getGameModel().current_players_object, "All money gone");

        }

        game.change_player(next_players);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        endTurn();
        game.getView().btn_roll.setEnabled(true);
    }

}
