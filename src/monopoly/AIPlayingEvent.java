package monopoly;

import monopoly.Model.Players;

public class AIPlayingEvent {
    private MonopolyGUI game;
    private Players players;
    private int temp;

    public AIPlayingEvent(MonopolyGUI monopolyGUI, Players players) {
        this.game = monopolyGUI;
        this.players = players;
    }

    public void aiTurn() {

        if (players.checkAI() == true) {
            game.btn_roll.setEnabled(false);
            new RollActionEvent(game).rollDice();
        }

    }
}
