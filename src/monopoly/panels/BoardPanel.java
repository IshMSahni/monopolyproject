package monopoly.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel to show propertys
 */
public class BoardPanel extends JPanel {

    ArrayList<Color> players_colors = new ArrayList<>();

    Color colors[] = {
            new Color(0xF44336), new Color(0xFFA726),
            new Color(0xFFFF00), new Color(0x00E676),
            new Color(0x03A9F4), new Color(0x512DA8)
    };

    /**
     * Add player to panel
     * @param color
     */
    public void add_player(Color color){
        players_colors.add(color);
    }

    /**
     * Clear panel
     */
    public void clear_players(){
        players_colors.clear();
    }

    /**
     * Paint nodes to panel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i < players_colors.size(); i++){
            g.setColor(players_colors.get(i));
            if(i < 2){
                g.fillOval(3 + 12 * i, 15, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(3 + 12 * i, 15, 10, 10);
            }
            else{
                g.fillOval(3 + 12 * i, 25, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(3 + 12 * i, 25, 10, 10);
            }

        }

    }


}
