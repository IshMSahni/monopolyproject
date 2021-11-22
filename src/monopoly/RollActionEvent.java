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
    private int doublesIncrement;


    public RollActionEvent(MonopolyGUI game){
        this.game = game;
        this.doublesIncrement = 0;

    }

        @Override
        public void actionPerformed(ActionEvent e) {
           rollDice();


        }

        public void rollDice(){
            System.err.println("Roll!");
            this.dice_point1 = new Random().nextInt(6) + 1;
            this.dice_point = new Random().nextInt(6) + 1;
//           this.dice_point1 = 3;
//            this.dice_point = 3;

            this.free_parking_money = 0;
            game.btn_roll.setEnabled(false);
            Players player = game.current_players_object;
            new Thread(() -> {
                jailEvaluation(player);
                game.update_propertyp();
                game.update_player_info();
                game.btn_roll.setEnabled(false);
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
        doublesIncrement = 0;
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

    public void playerTurn(){
        Players player = game.current_players_object;
        DiceResult roll = this.RollDice();

        game.final_dice_point = roll.first + roll.second;

        game.add_message("Player: " + player.getName() + " Rolled " + game.final_dice_point);

        player.go(game.final_dice_point);


        game.apply_colors();

        Integer new_position =player.getPosition();

        Property current_board = game.board.getProperties().get(new_position);

        if ((!current_board.isOwned() && !current_board.isSpecial() && !game.current_players_object.checkAI()) || (!current_board.isOwned() && current_board.isSpecialBuyable() && !game.current_players_object.checkAI())) {
            if (current_board.isSpecialBuyable()) {
                game.add_message(
                        "" +
                                player.getName() +
                                " landed on special board: " + current_board.getName());
            }
            Integer user_select = JOptionPane.showConfirmDialog(game.self, "This property is available for purchase for $" + current_board.getCost() + ". \n Would you like to buy " + game.board.propertyholder.get(current_board.getPosition()).getName() + "?", "Buy", JOptionPane.YES_NO_OPTION);
            if (user_select == JOptionPane.YES_OPTION) {
                if (player.getMoney() >= current_board.getCost()) {
                    player.setMoney(player.getMoney() - current_board.getCost());
                    current_board.setOwner(player.getName());
                } else {
                    JOptionPane.showMessageDialog(game.self, "Not enough money!");
                }
            }
        }
        else if((!current_board.isOwned() && !current_board.isSpecial() && game.current_players_object.checkAI()) || (!current_board.isOwned() && current_board.isSpecialBuyable()) && game.current_players_object.checkAI()){
            game.btn_roll.setEnabled(false);
            if (game.current_players_object.getMoney() >= current_board.getCost()) {
                game.current_players_object.setMoney(game.current_players_object.getMoney() - current_board.getCost());
                current_board.setOwner(game.current_players_object.getName());
            }
        }

        else if(current_board.isOwned()){
            PayRent(game.current_players_object, current_board.getRent(current_board.getCost()), current_board);
        }


        else if (current_board.isSpecial()) {
            game.add_message(
                    "" +
                            player.getName() +
                            " landed on special board: " + current_board.getName());
            if (!current_board.isSpecialBuyable()) {
                if (current_board.getName().equals("Luxury Tax")) {
                    if (player.getMoney() >= 75) {
                        player.setMoney(player.getMoney() - 75);
                        game.add_message("Player: " + player.getName() + " paid $75 in Luxury Tax");
                        free_parking_money += 75;
                    } else {
                        BankruptcyDeclare(player);
                    }
                } else if (current_board.getName().equals("Income Tax")) {
                    if (player.getMoney() >= 200) {
                        player.setMoney(player.getMoney() - 200);
                        game.add_message("Player: " + player.getName() + " paid $200 in Luxury Tax");
                        free_parking_money += 200;
                    } else {
                        BankruptcyDeclare(player);
                    }
                } else if (current_board.getName().equals("Free Parking")) {
                    player.setMoney(player.getMoney() + free_parking_money);
                    game.add_message("Player: " + player.getName() + "Just got a ton of money! They got: $" + free_parking_money + ".");
                    free_parking_money = 0;
                }
                else if (current_board.getName().equals("Go To Jail")) {
                    goToJail();
                } else if (current_board.getPosition() - game.final_dice_point <= 34 && current_board.getPosition() - game.final_dice_point >= 22 && (current_board.getPosition() >= 0) && current_board.getPosition() <= 12) {
                    player.setMoney(player.getMoney() + 200);
                    game.add_message("Player: " + player.getName() + "Just passed Go! They got: $" + 200 + ".");
                }
            } else {
                //Utility
                if ((current_board.getName().equals("Water Works") || current_board.getName().equals("Electric Company"))) {
                    if (current_board.getOwner().equals(player.getName())) {
                    } else {
                        Integer rent = current_board.getUtilityRent(game.board, game.final_dice_point, current_board.getOwner());
                        PayRent(player, rent, current_board);
                    }
                }
                //railroads
                else {
                    if (current_board.getOwner().equals(player.getName())) {
                    } else {
                        Integer rent = current_board.getRailRoadRent(current_board.getCost(), game.board, current_board.getOwner());
                        PayRent(player, rent, current_board);
                    }
                }
            }

        } else {
            if (current_board.getOwner().equals(player.getName())) {
            } else {
                Integer rent = current_board.getRent(current_board.getCost());

            }
        }


//        if(roll.first == roll.second && doublesIncrement < 3 && game.current_players_object.checkAI()){
//            game.add_message("Player: " + player.getName() + "rolled a double!");
//            doublesIncrement++;
//            playerTurn();
//        }
//
//        else if(doublesIncrement == 3){
//            game.add_message("Oh no! Player: " + player.getName() + "rolled a double 3 times in a row! They are now in jail!");
//            goToJail();
//
//        }

    }

    private void BankruptcyDeclare(Players player){
        game.add_message("Oh no! Player: " + player.getName() + "is broke and has to declare bankruptcy!");
        new Bankrupt().DeclareSpecificBankruptcy(player, game.board);
        game.remove_player(player, "Surrender");

    }

    private void PayRent(Players player, Integer rent, Property current_board){
        if (player.getMoney() >= rent) {
            player.setMoney(player.getMoney() - rent);
            Players owner_players = game.find_player_by_name(current_board.getOwner());
            owner_players.setMoney(owner_players.getMoney() + rent);
            game.add_message("Player: " + player.getName() + " paid rent to Player: " + owner_players.getName() + " for $" + rent + "");
        } else {
            BankruptcyDeclare(player);
        }

    }

    public void jailEvaluation(Players player){

        if (player.getInJail()){
            player.incrementTurnsInJail();
            game.add_message(
                    "Player: " +
                            player.getName() +
                            " is currently in jail. This is turn " + player.getTurnsInJail() + " in jail." );
            if(player.getTurnsInJail() <= 4) {

                if (player.getMoney() >= 50){

                    if(game.current_players_object.checkAI()){
                        player.setMoney(player.getMoney() - 50);
                        game.add_message("Player: " +
                                player.getName() +
                                " paid $50 to get out of jail.");
                        player.getOutofJail();
                        playerTurn();
                    }
                    else{
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
                        player.setMoney(player.getMoney() - 50);
                        game.add_message("Player: " +
                                player.getName() +
                                " paid $50 to get out of jail.");
                        player.getOutofJail();
                        playerTurn();
                    }
                }} else {
                    if (game.current_players_object.checkAI()) {
                        DiceResult roll = this.RollDice();
                        if (roll.first == roll.second) {
                            player.setTurnsInJail();
                            player.getOutofJail();
                            playerTurn();
                        }
                    } else {
                        String[] buttons = {"Roll for Doubles"};
                        int returnValue = JOptionPane.showOptionDialog(game.self, "You can only roll for doubles.", "Jail options",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                        if (returnValue == 0) {
                            DiceResult roll = this.RollDice();
                            if (roll.first == roll.second) {
                                player.setTurnsInJail();
                                player.getOutofJail();
                                playerTurn();
                            }
                        }
                    }
                }}
            else {
                player.setTurnsInJail();
                game.add_message(
                        "Player: " +
                                player.getName() +
                                " has to pay $50 to get out of jail.");
                Integer jailMoney = 50;
                if (player.getMoney() >= jailMoney) {
                    player.setMoney(player.getMoney() - jailMoney);

                    game.add_message("Player: " +
                            player.getName() +
                            " paid $50 to get out of jail.");
                    player.getOutofJail();
                    playerTurn();
                }
                else{
                    BankruptcyDeclare(player);
                }
            }
        }
        else {
            playerTurn();
        }
    }



}


