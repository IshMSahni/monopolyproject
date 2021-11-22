package monopoly;

import monopoly.Model.Players;
import monopoly.Model.Property;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class RollActionEvent implements ActionListener {
    private MonopolyGUI game;
    private int dice_point1;
    private int dice_point;
    private int free_parking_money;

    public RollActionEvent(MonopolyGUI game){
        this.game = game;
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.err.println("Roll!");
            this.dice_point1 = new Random().nextInt(6) + 1;
            this.dice_point = new Random().nextInt(6) + 1;
            this.free_parking_money = 0;
            game.btn_roll.setEnabled(false);
            Players player = game.current_players_object;
            new Thread(() -> {
                if (player.getInJail()){
                    player.incrementTurnsInJail();
                    game.add_message(
                            "Player: " +
                                    player.getName() +
                                    " is currently in jail. This is turn " + player.getTurnsInJail() + " in jail." );
                    if(player.getTurnsInJail() <= 4) {
                        if (player.getMoney() >= 50){
                            String[] buttons = {"Roll for Doubles", "Pay $50"};
                                  int returnValue = JOptionPane.showOptionDialog(game.self, "You can either roll for doubles or get out of jail by paying $50.", "Jail options",
                                          JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                         if (returnValue == 0){
                             DiceResult roll = this.RollDice();
                             if (roll.first == roll.second){
                                 player.setTurnsInJail();
                                 player.getOutofJail();
                                 playerTurn();
                             }
                         } else {
                             player.setMoney(game.current_players_object.getMoney() - 50);
                             game.add_message("Player: " +
                                     player.getName() +
                                     " paid $50 to get out of jail.");
                             player.getOutofJail();
                             playerTurn();
                         }
                        } else{

                          String[] buttons = {"Roll for Doubles"};
                          int returnValue = JOptionPane.showOptionDialog(game.self, "You can only roll for doubles.", "Jail options",
                                  JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                          if (returnValue == 0){
                              DiceResult roll = this.RollDice();
                              if (roll.first == roll.second){
                                  player.setTurnsInJail();
                                  player.getOutofJail();
                                  playerTurn();
                              }
                          }
                      }
                    }
                    else {
                        player.setTurnsInJail();
                        game.add_message(
                                "Player: " +
                                        game.current_players_object.getName() +
                                        " has to pay $50 to get out of jail.");
                        Integer jailMoney = 50;
                        if (game.current_players_object.getMoney() >= jailMoney) {
                            game.current_players_object.setMoney(game.current_players_object.getMoney() - jailMoney);

                            game.add_message("Player: " +
                                    game.current_players_object.getName() +
                                    " paid $50 to get out of jail.");
                            player.getOutofJail();
                            playerTurn();
                        }
                        else{
                            game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                            new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                            game.remove_player(player, "Surrender");
                        }
                    }
                }
                else {
                    playerTurn();
                }

                    game.update_propertyp();

                    game.update_player_info();
            }).start();


        }

    final class DiceResult {
        private final int first;
        private final int second;

        public DiceResult(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    private DiceResult RollDice(){
        int i = 1;
        while (i < 15) {
            game.panel_dice.set_prop(new Random().nextInt(6) + 1);
            game.panel_dice2.set_prop(new Random().nextInt(6) + 1);
            game.panel_dice.repaint();
            game.panel_dice2.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                System.out.println("There is some real issue with this program in the rolling function.");
            }
            i++;
        }
        game.panel_dice.set_prop(dice_point);
        game.panel_dice2.set_prop(dice_point1);
        game.panel_dice.repaint();
        game.panel_dice2.repaint();

        return new DiceResult(dice_point,dice_point1);
    }

    private void goToJail(){
        Players player = game.current_players_object;
        game.add_message("Oh no! Player: " + player.getName() + " is going to jail.");
        player.setPosition(8);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        game.apply_colors();
        player.goToJail();
    }

    private void playerTurn(){
        Players player = game.current_players_object;
        DiceResult roll = this.RollDice();
        game.final_dice_point = roll.first + roll.second;

        game.add_message("Player: " + game.current_players_object.getName() + " Rolled " + game.final_dice_point);

        game.current_players_object.go(game.final_dice_point);

        game.apply_colors();

        Integer new_position = game.current_players_object.getPosition();

        Property current_board = game.board.getProperties().get(new_position);

        if ((!current_board.isOwned() && !current_board.isSpecial()) || (!current_board.isOwned() && current_board.isSpecialBuyable())) {
            if (current_board.isSpecialBuyable()) {
                game.add_message(
                        "" +
                                game.current_players_object.getName() +
                                " landed on special board: " + current_board.getName());
            }
            Integer user_select = JOptionPane.showConfirmDialog(game.self, "This property is available for purchase for $" + current_board.getCost() + ". \n Would you like to buy " + game.board.propertyholder.get(current_board.getPosition()).getName() + "?", "Buy", JOptionPane.YES_NO_OPTION);
            if (user_select == JOptionPane.YES_OPTION) {
                if (game.current_players_object.getMoney() >= current_board.getCost()) {
                    game.current_players_object.setMoney(game.current_players_object.getMoney() - current_board.getCost());
                    current_board.setOwner(game.current_players_object.getName());
                } else {
                    JOptionPane.showMessageDialog(game.self, "Not enough money!");
                }
            }
        } else if (current_board.isSpecial()) {
            game.add_message(
                    "" +
                            game.current_players_object.getName() +
                            " landed on special board: " + current_board.getName());
            if (!current_board.isSpecialBuyable()) {
                if (current_board.getName().equals("Luxury Tax")) {
                    if (game.current_players_object.getMoney() >= 75) {
                        game.current_players_object.setMoney(game.current_players_object.getMoney() - 75);
                        game.add_message("Player: " + game.current_players_object.getName() + " paid $75 in Luxury Tax");
                        free_parking_money += 75;
                    } else {
                        new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                        game.remove_player(game.current_players_object, "Surrender");
                        game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                    }
                } else if (current_board.getName().equals("Income Tax")) {
                    if (game.current_players_object.getMoney() >= 200) {
                        game.current_players_object.setMoney(game.current_players_object.getMoney() - 200);
                        game.add_message("Player: " + game.current_players_object.getName() + " paid $200 in Luxury Tax");
                        free_parking_money += 200;
                    } else {
                        game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                        new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                        game.remove_player(game.current_players_object, "Surrender");
                    }
                } else if (current_board.getName().equals("Free Parking")) {
                    game.current_players_object.setMoney(game.current_players_object.getMoney() + free_parking_money);
                    game.add_message("Player: " + game.current_players_object.getName() + "Just got a ton of money! They got: $" + free_parking_money + ".");
                    free_parking_money = 0;
                }
                else if (current_board.getName().equals("Go To Jail")) {
                    game.add_message("Oh no! Player: " + game.current_players_object.getName() + " is going to jail.");
                    game.current_players_object.setPosition(8);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    game.apply_colors();
                    player.goToJail();
                } else if (current_board.getPosition() - game.final_dice_point <= 34 && current_board.getPosition() - game.final_dice_point >= 22 && (current_board.getPosition() >= 0) && current_board.getPosition() <= 12) {
                    game.current_players_object.setMoney(game.current_players_object.getMoney() + 200);
                    game.add_message("Player: " + game.current_players_object.getName() + "Just passed Go! They got: $" + 200 + ".");
                }
            } else {
                //Utility
                if ((current_board.getName().equals("Water Works") || current_board.getName().equals("Electric Company"))) {
                    if (current_board.getOwner().equals(game.current_players_object.getName())) {
                    } else {
                        Integer rent = current_board.getUtilityRent(game.board, game.final_dice_point, current_board.getOwner());
                        if (game.current_players_object.getMoney() >= rent) {
                            game.current_players_object.setMoney(game.current_players_object.getMoney() - rent);
                            Players owner_players = game.find_player_by_name(current_board.getOwner());
                            owner_players.setMoney(owner_players.getMoney() + rent);
                            game.add_message("Player: " + game.current_players_object.getName() + " paid rent to Player: " + owner_players.getName() + " for $" + rent + "");
                        } else {
                            game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                            new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                            game.remove_player(game.current_players_object, "Surrender");
                        }
                    }
                }
                //railroads
                else {
                    if (current_board.getOwner().equals(game.current_players_object.getName())) {
                    } else {
                        Integer rent = current_board.getRailRoadRent(current_board.getCost(), game.board, current_board.getOwner());
                        if (game.current_players_object.getMoney() >= rent) {
                            game.current_players_object.setMoney(game.current_players_object.getMoney() - rent);
                            Players owner_players = game.find_player_by_name(current_board.getOwner());
                            owner_players.setMoney(owner_players.getMoney() + rent);
                            game.add_message("Player: " + game.current_players_object.getName() + " paid rent to Player: " + owner_players.getName() + " for $" + rent + "");
                        } else {
                            game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                            new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                            game.remove_player(game.current_players_object, "Surrender");
                        }
                    }
                }
            }

        } else {
            if (current_board.getOwner().equals(game.current_players_object.getName())) {
            } else {
                Integer rent = current_board.getRent(current_board.getCost());
                if (game.current_players_object.getMoney() >= rent) {
                    game.current_players_object.setMoney(game.current_players_object.getMoney() - rent);
                    Players owner_players = game.find_player_by_name(current_board.getOwner());
                    owner_players.setMoney(owner_players.getMoney() + rent);
                    game.add_message("Player: " + game.current_players_object.getName() + " paid rent to Player: " + owner_players.getName() + " for $" + rent + "");
                } else {
                    new Bankrupt().DeclareSpecificBankruptcy(game.current_players_object, game.board);
                    game.remove_player(game.current_players_object, "Surrender");
                    game.add_message("Oh no! Player: " + game.current_players_object.getName() + "is broke and has to declare bankruptcy!");
                }
            }
        }
    }



}


