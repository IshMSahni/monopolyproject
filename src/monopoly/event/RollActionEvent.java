package monopoly.event;

import monopoly.Bankrupt;
import monopoly.Config;
import monopoly.GameControl;
import monopoly.Model.Board;
import monopoly.Model.Player;
import monopoly.Model.Property;
import monopoly.MonopolyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RollActionEvent implements ActionListener {
    private final GameControl game;
    private int dice_point1;
    private int dice_point;
    private int free_parking_money;
    private int doublesIncrement;


    public RollActionEvent(GameControl game){
        this.game = game;
        this.doublesIncrement = 0;

    }

    boolean flag_passed_go = false;
    int last_position = 0;

    void passed_go_check_initialize() {
        flag_passed_go = false;
        last_position = game.getGameModel().current_players_object.getPosition();
    }

    boolean passed_go_check() {
        return last_position > game.getGameModel().current_players_object.getPosition();
    }

        @Override
        public void actionPerformed(ActionEvent e) {
           rollDice();
        }

        public void rollDice(){
            System.err.println("Roll!");
           this.dice_point1 = new Random().nextInt(6) + 1;
           this.dice_point = new Random().nextInt(6) + 1;


            this.free_parking_money = 0;
            game.getView().btn_roll.setEnabled(false);
            Player player = game.getGameModel().current_players_object;
            new Thread(() -> {
                passed_go_check_initialize();
                jailEvaluation(player);
                game.updateProperty();
                if(passed_go_check()){
                    game.add_message("Player: " + player.getName() + " passed GO!");
                    game.getGameModel().current_players_object.setMoney(game.getGameModel().current_players_object.getMoney() + 200);

                }
                game.update_player_info();
                game.getView().btn_roll.setEnabled(false);
                game.next_player();
                game.getView().btn_roll.setEnabled(true);
                game.getView().setup_btn_end_round(true);

            }).start();


            game.getView().btn_roll.setEnabled(true);
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
            game.getView().panel_dice.set_prop(new Random().nextInt(6) + 1);
            game.getView().panel_dice2.set_prop(new Random().nextInt(6) + 1);

            game.getView().panel_dice.repaint();
            game.getView().panel_dice2.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                System.out.println("There is some real issue with this program in the rolling function.");
            }
            i++;
        }
        game.getView().panel_dice.set_prop(dice_point);
        game.getView().panel_dice2.set_prop(dice_point1);
        game.getView().panel_dice.repaint();
        game.getView().panel_dice2.repaint();

        return new DiceResult(dice_point,dice_point1);
    }

    private void goToJail(){
        Player player = game.getGameModel().current_players_object;
        doublesIncrement = 1;
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
        Player player = game.getGameModel().current_players_object;
        DiceResult roll = this.RollDice();

        game.final_dice_point = roll.first + roll.second;

        game.add_message("Player: " + player.getName() + " Rolled " + game.final_dice_point);

        player.go(game.final_dice_point);


        game.apply_colors();

        Integer new_position =player.getPosition();

        Property current_board = game.getGameModel().getBoard().getProperties().get(new_position);

        if ((!current_board.isOwned() && !current_board.isSpecial() && !game.getGameModel().current_players_object.checkAI())
                || (!current_board.isOwned() && current_board.isSpecialBuyable() && !game.getGameModel().current_players_object.checkAI())) {
            if (current_board.isSpecialBuyable()) {
                game.add_message(
                        "" +
                                player.getName() +
                                " landed on special board: " + current_board.getName());
            }
            Integer user_select = JOptionPane.showConfirmDialog(game.getView(), "This property is available for purchase for "+ Config.configurable_dollar_sign + current_board.getCost() + ". \n Would you like to buy " + game.getGameModel().getBoard().propertyholder.get(current_board.getPosition()).getName() + "?", "Buy", JOptionPane.YES_NO_OPTION);
            if (user_select == JOptionPane.YES_OPTION) {
                if (player.getMoney() >= current_board.getCost()) {
                    player.setMoney(player.getMoney() - current_board.getCost());
                    current_board.setOwner(player.getName());
                } else {
                    JOptionPane.showMessageDialog(game.getView(), "Not enough money!");
                }
            }
        }
        else if((!current_board.isOwned() && !current_board.isSpecial() && game.getGameModel().current_players_object.checkAI()) || (!current_board.isOwned() && current_board.isSpecialBuyable()) && game.getGameModel().current_players_object.checkAI()){
            game.getView().btn_roll.setEnabled(false);
            if (game.getGameModel().current_players_object.getMoney() >= current_board.getCost()) {
                game.getGameModel().current_players_object.setMoney(game.getGameModel().current_players_object.getMoney() - current_board.getCost());
                current_board.setOwner(game.getGameModel().current_players_object.getName());
            }
        }

        else if(current_board.isOwned()){
            PayRent(game.getGameModel().current_players_object, current_board.getRent(current_board.getCost()), current_board);
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
                        game.add_message("Player: " + player.getName() + " paid " + Config.configurable_dollar_sign + "75 in Luxury Tax");
                        free_parking_money += 75;
                    } else {
                        BankruptcyDeclare(player);
                    }
                } else if (current_board.getName().equals("Income Tax")) {
                    if (player.getMoney() >= 200) {
                        player.setMoney(player.getMoney() - 200);
                        game.add_message("Player: " + player.getName() + " paid " + Config.configurable_dollar_sign + "200 in Luxury Tax");
                        free_parking_money += 200;
                    } else {
                        BankruptcyDeclare(player);
                    }
                } else if (current_board.getName().equals("Free Parking")) {
                    player.setMoney(player.getMoney() + free_parking_money);
                    game.add_message("Player: " + player.getName() + "Just got a ton of money! They got: " + Config.configurable_dollar_sign + free_parking_money + ".");
                    free_parking_money = 0;
                }
                else if (current_board.getName().equals("Go To Jail")) {
                    goToJail();
                } else if (current_board.getPosition() - game.final_dice_point <= 34 && current_board.getPosition() - game.final_dice_point >= 22 && (current_board.getPosition() >= 0) && current_board.getPosition() <= 12) {
                    player.setMoney(player.getMoney() + 200);
                    game.add_message("Player: " + player.getName() + "Just passed Go! They got: " + Config.configurable_dollar_sign + 200 + ".");
                }
            } else {
                //Utility
                if ((current_board.getName().equals("Water Works") || current_board.getName().equals("Electric Company"))) {
                    if (current_board.getOwner().equals(player.getName())) {
                    } else {
                        Integer rent = current_board.getUtilityRent(game.getGameModel().getBoard(), game.final_dice_point, current_board.getOwner());
                        PayRent(player, rent, current_board);
                    }
                }
                //railroads
                else {
                    if (current_board.getOwner().equals(player.getName())) {
                    } else {
                        Integer rent = current_board.getRailRoadRent(current_board.getCost(), game.getGameModel().getBoard(), current_board.getOwner());
                        PayRent(player, rent, current_board);
                    }
                }
            }

        } else {
            if (current_board.getOwner().equals(player.getName())) {
                if(current_board.getNumHotels() == 1){
                }else{
                    if(current_board.getNumHouses() == 0){
                        if(checkSameOwner(game.getGameModel().getBoard(), current_board)){
                            Integer user_select = JOptionPane.showConfirmDialog(game.getView(), "You can build houses on this property for " + Config.configurable_dollar_sign + current_board.getHousePrice() + ". \n Would you like to build on " + game.getGameModel().getBoard().propertyholder.get(current_board.getPosition()).getName() + "?", "Build Houses", JOptionPane.YES_NO_OPTION);
                            if(user_select == JOptionPane.YES_OPTION){
                                if(player.getMoney() > current_board.getHousePrice()){
                                    player.setMoney(player.getMoney() - current_board.getHousePrice());
                                    current_board.addHouse(1);
                                    JOptionPane.showMessageDialog(game.getView(), "You have built a house on this property!");
                                }else{
                                    JOptionPane.showMessageDialog(game.getView(), "Not enough money!");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(game.getView(), "You need to buy all properties in the same color to build house on them!");
                        }
                    }else{
                        if(checkMultipleHouses(game.getGameModel().getBoard(), current_board)){
                            Integer user_select = JOptionPane.showConfirmDialog(game.getView(), "You can build houses on this property for " + Config.configurable_dollar_sign + current_board.getHousePrice() + ". \n Would you like to build on " + game.getGameModel().getBoard().propertyholder.get(current_board.getPosition()).getName() + "?", "Build Houses", JOptionPane.YES_NO_OPTION);
                            if(user_select == JOptionPane.YES_OPTION){
                                if(current_board.getNumHouses() != 4){
                                    if(player.getMoney() > current_board.getHousePrice()){
                                        player.setMoney(player.getMoney() - current_board.getHousePrice());
                                        current_board.addHouse(1);
                                        JOptionPane.showMessageDialog(game.getView(), "You have built a house on this property!");
                                    }else{
                                        JOptionPane.showMessageDialog(game.getView(), "Not enough money!");
                                    }
                                }else if(current_board.getNumHouses() == 4) {
                                    if (player.getMoney() > current_board.getHousePrice()) {
                                        player.setMoney(player.getMoney() - current_board.getHousePrice());
                                        current_board.clearNumHouse();
                                        current_board.addHotel(1);
                                        JOptionPane.showMessageDialog(game.getView(), "You have built a hotel on this property!");
                                    }else{
                                        JOptionPane.showMessageDialog(game.getView(), "Not enough money!");
                                    }
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(game.getView(), "You need to have " + current_board.getNumHouses() + " houses on each property of the same color to build more house!");
                        }
                    }
                }
            } else {
                Integer rent = current_board.getRent(current_board.getCost());

            }
        }
        if(roll.first == roll.second && doublesIncrement < 2){
            doublesIncrement++;
            game.add_message("Player: " + player.getName() + "rolled a double!");
            if(game.getGameModel().current_players_object.checkAI()) {
                try {
                    Thread.sleep(500);
                    rollDice();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            else{
                Integer user_select = JOptionPane.showConfirmDialog(game.getView(),"Player: " + player.getName() + " rolled a double!\n Would you like to roll again?");
                if(user_select == JOptionPane.YES_OPTION){
                    rollDice();
                }
                else{}

            }
        }

        else if(doublesIncrement == 2){
            game.add_message("Oh no! Player: " + player.getName() + "rolled a double 3 times in a row! They are now in jail!");
            goToJail();

        }

        if(player.getMoney() < 0){
            game.remove_player(player, "You have lost all your money!");
        }
        
    }

    private void BankruptcyDeclare(Player player){
        game.add_message("Oh no! Player: " + player.getName() + "is broke and has to declare bankruptcy!");
        new Bankrupt().DeclareSpecificBankruptcy(player, game.getGameModel().getBoard());
        game.remove_player(player, "Surrender");

    }

    private void PayRent(Player player, Integer rent, Property current_board){
        if (player.getMoney() >= rent) {
            player.setMoney(player.getMoney() - rent);
            Player owner_players = game.find_player_by_name(current_board.getOwner());
            owner_players.setMoney(owner_players.getMoney() + rent);
            game.add_message("Player: " + player.getName() + " paid rent to Player: " + owner_players.getName() + " for " + Config.configurable_dollar_sign + rent + "");
        } else {
            BankruptcyDeclare(player);
        }

    }

    public void jailEvaluation(Player player){

        if (player.getInJail()){
            player.incrementTurnsInJail();
            game.add_message(
                    "Player: " +
                            player.getName() +
                            " is currently in jail. This is turn " + player.getTurnsInJail() + " in jail." );
            if(player.getTurnsInJail() <= 4) {

                if (player.getMoney() >= 50){

                    if(game.getGameModel().current_players_object.checkAI()){
                        player.setMoney(player.getMoney() - 50);
                        game.add_message("Player: " +
                                player.getName() +
                                " paid " + Config.configurable_dollar_sign +  "50 to get out of jail.");
                        player.getOutofJail();
                        playerTurn();
                    }
                    else{
                    String[] buttons = {"Roll for Doubles", "Pay " + Config.configurable_dollar_sign + "50"};
                    int returnValue = JOptionPane.showOptionDialog(game.getView(), "You can either roll for doubles or get out of jail by paying " + Config.configurable_dollar_sign + " 50.", "Jail options",
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
                                " paid " + Config.configurable_dollar_sign + "50 to get out of jail.");
                        player.getOutofJail();
                        playerTurn();
                    }
                }} else {
                    if (game.getGameModel().current_players_object.checkAI()) {
                        DiceResult roll = this.RollDice();
                        if (roll.first == roll.second) {
                            player.setTurnsInJail();
                            player.getOutofJail();
                            playerTurn();
                        }
                    } else {
                        String[] buttons = {"Roll for Doubles"};
                        int returnValue = JOptionPane.showOptionDialog(game.getView(), "You can only roll for doubles.", "Jail options",
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
                                " has to pay " + Config.configurable_dollar_sign + "50 to get out of jail.");
                Integer jailMoney = 50;
                if (player.getMoney() >= jailMoney) {
                    player.setMoney(player.getMoney() - jailMoney);

                    game.add_message("Player: " +
                            player.getName() +
                            " paid " + Config.configurable_dollar_sign + "50 to get out of jail.");
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

    private boolean checkSameOwner(Board board, Property property){
        ArrayList<Property> properties = new ArrayList<Property>();
        if(!property.isSpecial()){
            for(int i = property.getPosition() - 3; i < property.getPosition() + 3; i++){
                if(!board.getProperties().get(i).isSpecial()){
                    if(board.getProperties().get(i).getGroupNum() == property.getGroupNum()){
                        properties.add(board.getProperties().get(i));
                    }
                }
            }
            for(int j = 0; j < properties.size(); j++){
                if(properties.get(j).getOwner() != property.getOwner()){
                    return false;
                }
            }
            return true;
        }else return false;
    }

    private boolean checkMultipleHouses(Board board, Property property){
        ArrayList<Property> properties = new ArrayList<Property>();
        if(!property.isSpecial()){
            if(property.getNumHouses() == 0){
                return false;
            }else{
                properties.add(property);
            }
            for(int i = property.getPosition() - 3; i < property.getPosition() + 3; i++){
                if(!board.getProperties().get(i).isSpecial()){
                    if(board.getProperties().get(i).getGroupNum() == property.getGroupNum()){
                        properties.add(board.getProperties().get(i));
                    }
                }
            }
            for(int j = 0; j < properties.size(); j++){
                if(properties.get(j).getNumHouses() < property.getNumHouses()){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }



}


