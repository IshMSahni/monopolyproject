package monopoly;

import monopoly.Model.Player;
import monopoly.Model.Property;
import monopoly.MonopolyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RollActionEvent implements ActionListener {
    private MonopolyGUI game;
    private int dice_point1;
    private int dice_point;

    public RollActionEvent(MonopolyGUI game){
        this.game = game;
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.err.println("Roll!");
            this.dice_point1 = new Random().nextInt(6) + 1;
            this.dice_point = new Random().nextInt(6) + 1;
            game.btn_roll.setEnabled(false);

            new Thread(){
                @Override
                public void run() {
                    int i = 1;
                    while (i < 15) {
                        game.panel_dice.set_prop(new Random().nextInt(6) + 1);
                        game.panel_dice2.set_prop(new Random().nextInt(6) + 1);
                        game.panel_dice.repaint();
                        game.panel_dice2.repaint();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                        i++;
                    }
                    game.panel_dice.set_prop(dice_point);
                    game.panel_dice2.set_prop(dice_point1);
                    game.panel_dice.repaint();
                    game.panel_dice2.repaint();
                    game.final_dice_point = dice_point + dice_point1;

                    game.add_message("Player: " + game.current_player_object.getName() + " Rolled " + game.final_dice_point);

                    game.current_player_object.go(game.final_dice_point);

                    game.apply_colors();

                    Integer new_position = game.current_player_object.getPosition();

                    Property current_board = game.board.getProperties().get(new_position);

                    if (!current_board.isOwned() && !current_board.isSpecial()) {
                        Integer user_select = JOptionPane.showConfirmDialog(game.self, "This property is available for purchase for $" + current_board.getCost() + ". \n Would you like to buy " + game.board.propertyholder.get(current_board.getPosition()).getName() + "?", "Buy", JOptionPane.YES_NO_OPTION);
                        if (user_select == JOptionPane.YES_OPTION) {
                            if (game.current_player_object.getMoney() >= current_board.getCost()) {
                                game.current_player_object.setMoney(game.current_player_object.getMoney() - current_board.getCost());
                                current_board.setOwner(game.current_player_object.getName());
                            }
                            else{
                                JOptionPane.showMessageDialog(game.self, "Not enough money!");
                            }
                        }
                        else{ }
                    }
                    else if (current_board.isSpecial()) {
                        game.add_message(
                                "Player: " +
                                        game.current_player_object.getName() +
                                        " landed on special board: " + current_board.getName());
                    }
                    else {
                        if (current_board.getOwner().equals(game.current_player_object.getName())) { }
                        else{
                            Integer rent = current_board.getRent(current_board.getCost());
                            game.current_player_object.setMoney(game.current_player_object.getMoney() - rent);
                            Player owner_player = game.find_player_by_name(current_board.getOwner());
                            owner_player.setMoney(owner_player.getMoney() + rent);

                            game.add_message("Player: " + game.current_player_object.getName() + " paid rent to Player: " + owner_player.getName() + " for $" + rent + "");

                        }
                    }
                    game.update_propertyp();

                    game.update_player_info();

                }

            }.start();


        }


}


