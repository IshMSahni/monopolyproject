package monopoly.event;

import monopoly.GameControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadEvent implements ActionListener {

    GameControl game;

    public LoadEvent(GameControl _game){
        game = _game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("Load");
        game.loadGame();
    }

}
