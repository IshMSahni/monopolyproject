package test.monopoly;

import monopoly.DialogProvider;
import monopoly.Model.Players;
import monopoly.MonopolyGUI;

import org.junit.Assert;
import org.junit.Test;
import test.monopoly.util.Reflection;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;


class MonopolyTest {

    @Test
    static void ask_for_player_number() {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Integer> expects = new ArrayList<>();
        expects.add(2);
        expects.add(3);
        expects.add(4);

        MonopolyGUI game = new MonopolyGUI();
        for(int i = 2; i <= 4; i++) {
            DialogProvider.result_input = i + "";
            DialogProvider.debug_mode = true;
            Class clazz = MonopolyGUI.class;
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equals("ask_for_player_number")) {
                    m.setAccessible(true);
                    try{
                        Integer result = (Integer) m.invoke(game);
                        results.add(result);
                    }
                    catch (Exception e){
                        e.printStackTrace(System.err);
                        fail();
                    }
                }
            }
        }
        Assert.assertEquals(expects, results);
    }

    @Test
    static void ask_for_player_name() {
        MonopolyGUI game = new MonopolyGUI();
        Method method = Reflection.get_method(MonopolyGUI.class, "ask_for_player_name");

        if(method == null) {
            fail();
        }

        DialogProvider.debug_mode = true;
        DialogProvider.result_input = "#Player#";

        try{
            String result = (String)method.invoke(game);
            assertEquals("#Player#", result);
        }
        catch (Exception e){
            e.printStackTrace(System.err);
            fail();
        }

    }

    @Test
    static void add_message(){
        MonopolyGUI game = new MonopolyGUI();
        Method method = Reflection.get_method(MonopolyGUI.class, "add_message");

        if(method == null) {
            fail();
        }

        try{
            DefaultListModel<String> message_model =  game.ut_get_message_model();
            message_model.clear();
            method.invoke(game, "DummyMessage");
            assertTrue(message_model.get(0).equals("DummyMessage"));
        }
        catch (Exception e){
            e.printStackTrace(System.err);
            fail();
        }
    }

    @Test
    static void update_player_info(){
        MonopolyGUI game = new MonopolyGUI();

        game.ut_setup_player_info();

        Method method = Reflection.get_method(MonopolyGUI.class, "update_player_info");

        ArrayList<String> results = new ArrayList<>();
        results.add("Player: Player_0    Money: 0$    Position: 0");
        results.add("Player: Player_1    Money: 0$    Position: 0");
        results.add("Player: Player_2    Money: 0$    Position: 0");
        results.add("Player: Player_3    Money: 0$    Position: 0");

        try{
            method.invoke(game);
            DefaultListModel<String> player_model = game.ut_get_player_model();
            for (int i = 0; i <player_model.size() ; i++) {
                assertTrue(results.get(i).equals(player_model.get(i)));
            }
        }
        catch (Exception e){
            e.printStackTrace(System.err);
        }

    }

    @Test
    static void change_player(){
        MonopolyGUI game = new MonopolyGUI();
        Method method = Reflection.get_method(MonopolyGUI.class, "change_player");

        if(method == null) {
            fail();
        }

        Players players = new Players("Player_0", 0, 0, false);

        try{
            DefaultListModel<String> message_model =  game.ut_get_message_model();
            message_model.clear();

            method.invoke(game, players);
            assertTrue(message_model.get(0).equals("It is now Player_0's turn."));
        }
        catch (Exception e){
            e.printStackTrace(System.err);
            fail();
        }
    }

    public static void main(String[] args) {
        ask_for_player_number();
        //ask_for_player_name(); This is made to be tested in a game that is already running.
        add_message();
        update_player_info();
        change_player();
    }
}