package monopoly.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to display dice
 */
public static class DicePanel extends JPanel {


    private int x, y, width, height;
    private int value = 1;

    /**
     * Set dice point
     * @param _value point to set
     */
    public void set_prop(int _value) {
        value = _value;
    }

    public DicePanel() {
        x = 0;
        y = 0;
        width = 70;
        height = 70;
    }

    /**
     * Draw dice
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draw dice process
     * @param g graphics
     */
    public void draw(Graphics g) {

        Graphics2D g2d1 = (Graphics2D)g;
        g2d1.setColor(Color.WHITE);
        Rectangle rect_base1 = new Rectangle(x, y, width, height);

        g2d1.setColor(Color.WHITE);
        g2d1.fill(rect_base1);

        g2d1.setColor(Color.RED);
        switch(value){
            case 1: {
                g2d1.fillOval(x + width / 4 * 1, y + height / 4 * 1, width / 2, height / 2);
                break;
            }
            case 2: {
                g2d1.fillOval(x + width / 8 * 2, y + height / 8 * 2, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 4, y + height / 8 * 4, width / 4, height / 4);
                break;
            }
            case 3: {
                g2d1.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 3, y + height / 8 * 3, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                break;
            }
            case 4: {
                g2d1.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 1, y + height / 8 * 5, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 5, y + height / 8 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                break;
            }
            case 5: {
                g2d1.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 1, y + height / 8 * 5, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 5, y + height / 8 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                g2d1.fillOval(x + width / 8 * 3, y + height / 8 * 3, width / 4, height / 4);
                break;
            }
            case 6: {
                g2d1.fillOval(x + width / 10 * 1, y + height / 10 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 10 * 1, y + height / 10 * 4, width / 4, height / 4);
                g2d1.fillOval(x + width / 10 * 1, y + height / 10 * 7, width / 4, height / 4);
                g2d1.fillOval(x + width / 10 * 6, y + height / 10 * 1, width / 4, height / 4);
                g2d1.fillOval(x + width / 10 * 6, y + height / 10 * 4, width / 4, height / 4);
                g2d1.fillOval(x + width / 10 * 6, y + height / 10 * 7, width / 4, height / 4);
                break;
            }

        }




        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        Rectangle rect_base = new Rectangle(x, y, width, height);

        g2d.setColor(Color.WHITE);
        g2d.fill(rect_base);

        g2d.setColor(Color.RED);
        switch(value){
            case 1: {
                g2d.fillOval(x + width / 4 * 1, y + height / 4 * 1, width / 2, height / 2);
                break;
            }
            case 2: {
                g2d.fillOval(x + width / 8 * 2, y + height / 8 * 2, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 4, y + height / 8 * 4, width / 4, height / 4);
                break;
            }
            case 3: {
                g2d.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 3, y + height / 8 * 3, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                break;
            }
            case 4: {
                g2d.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 1, y + height / 8 * 5, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 5, y + height / 8 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                break;
            }
            case 5: {
                g2d.fillOval(x + width / 8 * 1, y + height / 8 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 1, y + height / 8 * 5, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 5, y + height / 8 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 5, y + height / 8 * 5, width / 4, height / 4);
                g2d.fillOval(x + width / 8 * 3, y + height / 8 * 3, width / 4, height / 4);
                break;
            }
            case 6: {
                g2d.fillOval(x + width / 10 * 1, y + height / 10 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 10 * 1, y + height / 10 * 4, width / 4, height / 4);
                g2d.fillOval(x + width / 10 * 1, y + height / 10 * 7, width / 4, height / 4);
                g2d.fillOval(x + width / 10 * 6, y + height / 10 * 1, width / 4, height / 4);
                g2d.fillOval(x + width / 10 * 6, y + height / 10 * 4, width / 4, height / 4);
                g2d.fillOval(x + width / 10 * 6, y + height / 10 * 7, width / 4, height / 4);
                break;
            }

        }
        g2d1.dispose();
        g2d.dispose();

    }

}
}
