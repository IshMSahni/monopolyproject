package monopoly.event;

import monopoly.GameControl;
import monopoly.Model.Player;
import monopoly.MonopolyGUI;

public class AIPlayingEvent {
    private final GameControl game;
    private final Player players;

    public AIPlayingEvent(GameControl monopolyGUI, Player players) {
        this.game = monopolyGUI;
        this.players = players;
    }

    public void aiTurn() {

        if (players.checkAI() == true) {
            game.getView().setEnabled(false);
            new RollActionEvent(game).rollDice();
        }
        else{
            game.getView().setEnabled(true);
        }

    }
}
