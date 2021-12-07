package test.monopoly;

import monopoly.Config;
import monopoly.DialogProvider;
import monopoly.GameControl;
import monopoly.Model.Player;
import monopoly.MonopolyGUI;

import org.junit.Assert;
import org.junit.Test;
import test.monopoly.util.Reflection;


import javax.swing.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameControlTest {

    @Test
    public void add_message() {
        GameControl gameControl = new GameControl(null);
        gameControl.add_message("test");
        DefaultListModel model = gameControl.ut_get_message_model();
        assertEquals("test", model.get(model.size() - 1));
    }

    @Test
    public void update_player_info() {
        GameControl gameControl = new GameControl(null);
        gameControl.getGameModel().players.add(
                new Player("TestPlayer", 777, 7,false)
        );
        gameControl.update_player_info();
        DefaultListModel model = gameControl.ut_get_player_model();
        String player_info_text = "";
        for(int i = 0; i < model.size(); i++) {
            player_info_text += model.get(i);
        }
        assertEquals("Player: TestPlayer    Money: 777" + Config.configurable_dollar_sign + "    Position: 7", player_info_text);

    }

    @Test
    public void find_player_by_name() {
        GameControl gameControl = new GameControl(null);
        gameControl.getGameModel().players.add(
                new Player("TestPlayer", 777, 7,false)
        );
        Player player = gameControl.find_player_by_name("TestPlayer");
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    public void remove_player() {
        GameControl gameControl = new GameControl(null);
        Player player = new Player("TestPlayer", 777, 7,false);
        gameControl.getGameModel().players.add(
            player
        );
        gameControl.remove_player(player, "");
        assertEquals(0, gameControl.getGameModel().players.size());
    }




}
