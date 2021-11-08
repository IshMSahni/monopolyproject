package monopoly;

import monopoly.panels.BoardPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class is the main ui design class for the monopoly game.
 */
public class GameBoardDesign extends JFrame {

    public BoardPanel contentPane;
    public BoardPanel own1;
    public BoardPanel own2;
    public BoardPanel own3;
    public BoardPanel own4;
    public BoardPanel own5;
    public BoardPanel own6;
    public BoardPanel own7;
    public BoardPanel own8;
    public BoardPanel own9;
    public BoardPanel own10;
    public BoardPanel own11;
    public BoardPanel own12;
    public BoardPanel own13;
    public BoardPanel own14;
    public BoardPanel own15;
    public BoardPanel own16;
    public BoardPanel own17;
    public BoardPanel own18;
    public BoardPanel own19;
    public BoardPanel own20;
    public BoardPanel own21;
    public BoardPanel own22;
    public BoardPanel own23;
    public BoardPanel own24;
    public BoardPanel own25;
    public BoardPanel own26;

    public BoardPanel board_14;
    public BoardPanel board_15;
    public BoardPanel board_16;
    public BoardPanel board_17;
    public BoardPanel board_18;
    public BoardPanel board_19;
    public BoardPanel board_20;
    public BoardPanel board_21;
    public BoardPanel board_13;
    public BoardPanel board_12;
    public BoardPanel board_11;
    public BoardPanel board_10;
    public BoardPanel board_9;
    public BoardPanel board_8;
    public BoardPanel board_1;
    public BoardPanel board_26;
    public BoardPanel board_25;
    public BoardPanel board_24;
    public BoardPanel board_23;
    public BoardPanel board_22;
    public BoardPanel board_7;
    public BoardPanel board_6;
    public BoardPanel board_5;
    public BoardPanel board_4;
    public BoardPanel board_3;
    public BoardPanel board_2;

    public JList player_list;

    public DicePanel.DicePanel panel_dice;
    public DicePanel.DicePanel panel_dice2;
    public JButton btn_roll;
    public JButton btn_end_round;
    public JButton btn_surrender;

    public JLabel lblNewLabel;
    public JLabel lblNewLabel_1;

    public JLabel current_player;
    public JList message_list;




    /**
     * Create the frame.
     */
    public GameBoardDesign() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new BoardPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // Special positions
        own1 = new BoardPanel();
        own7 = new BoardPanel();
        own14 = new BoardPanel();
        own21 = new BoardPanel();

        board_14 = new BoardPanel();
        board_14.setBounds(10, 10, 50, 40);
        board_14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(board_14);

        own15 = new BoardPanel();
        own15.setBounds(70, 10, 50, 10);
        own15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own15);

        board_15 = new BoardPanel();
        board_15.setBounds(70, 10, 50, 40);
        board_15.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_15.setBackground(new Color(219,36,40));
        contentPane.add(board_15);

        own16 = new BoardPanel();
        own16.setBounds(130, 10, 50, 10);
        own16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own16);

        board_16 = new BoardPanel();
        board_16.setBounds(130, 10, 50, 40);
        board_16.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_16.setBackground(new Color(219,36,40));
        contentPane.add(board_16);

        own17 = new BoardPanel();
        own17.setBounds(190, 10, 50, 10);
        own17.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own17);

        board_17 = new BoardPanel();
        board_17.setBounds(190, 10, 50, 40);
        board_17.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_17.setBackground(new Color(219,36,40));
        contentPane.add(board_17);

        own18 = new BoardPanel();
        own18.setBounds(250, 10, 50, 10);
        own18.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own18);

        board_18 = new BoardPanel();
        board_18.setBounds(250, 10, 50, 40);
        board_18.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_18.setBackground(new Color(255,240,4));
        contentPane.add(board_18);

        own19 = new BoardPanel();
        own19.setBounds(310, 10, 50, 10);
        own19.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        contentPane.add(own19);

        board_19 = new BoardPanel();
        board_19.setBounds(310, 10, 50, 40);
        board_19.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_19.setBackground(new Color(255,240,4));
        contentPane.add(board_19);

        own20 = new BoardPanel();
        own20.setBounds(370, 10, 50, 10);
        own20.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own20);

        board_20 = new BoardPanel();
        board_20.setBounds(370, 10, 50, 40);
        board_20.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_20.setBackground(new Color(255,240,4));
        contentPane.add(board_20);

        board_21 = new BoardPanel();
        board_21.setBounds(430, 10, 50, 40);
        board_21.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(board_21);

        own13 = new BoardPanel();
        own13.setBounds(10, 60, 50, 10);
        own13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own13);

        board_13 = new BoardPanel();
        board_13.setBounds(10, 60, 50, 40);
        board_13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_13.setBackground(new Color(236,139,44));
        contentPane.add(board_13);

        own12 = new BoardPanel();
        own12.setBounds(10, 110, 50, 10);
        own12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own12);

        board_12 = new BoardPanel();
        board_12.setBounds(10, 110, 50, 40);
        board_12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_12.setBackground(new Color(236,139,44));
        contentPane.add(board_12);

        own11 = new BoardPanel();
        own11.setBounds(10, 160, 50, 10);
        own11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own11);

        board_11 = new BoardPanel();
        board_11.setBounds(10, 160, 50, 40);
        board_11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_11.setBackground(new Color(236,139,44));
        contentPane.add(board_11);

        own10 = new BoardPanel();
        own10.setBounds(10, 210, 50, 10);
        own10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own10);

        board_10 = new BoardPanel();
        board_10.setBounds(10, 210, 50, 40);
        board_10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_10.setBackground(new Color(197,56,132));
        contentPane.add(board_10);

        own9 = new BoardPanel();
        own9.setBounds(10, 260, 50, 10);
        own9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own9);

        board_9 = new BoardPanel();
        board_9.setBounds(10, 260, 50, 40);
        board_9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_9.setBackground(new Color(197,56,132));
        contentPane.add(board_9);

        own8 = new BoardPanel();
        own8.setBounds(10, 310, 50, 10);
        own8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own8);

        board_8 = new BoardPanel();
        board_8.setBounds(10, 310, 50, 40);
        board_8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_8.setBackground(new Color(197,56,132));
        contentPane.add(board_8);


        board_1 = new BoardPanel();
        board_1.setBounds(430, 310, 50, 40);
        board_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(board_1);

        own26 = new BoardPanel();
        own26.setBounds(430, 260, 50, 10);
        own26.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own26);

        board_26 = new BoardPanel();
        board_26.setBounds(430, 260, 50, 40);
        board_26.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_26.setBackground(new Color(0,102,164));
        contentPane.add(board_26);

        own25 = new BoardPanel();
        own25.setBounds(430, 210, 50, 10);
        own25.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own25);

        board_25 = new BoardPanel();
        board_25.setBounds(430, 210, 50, 40);
        board_25.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_25.setBackground(new Color(0,102,164));
        contentPane.add(board_25);

        own24 = new BoardPanel();
        own24.setBounds(430, 160, 50, 10);
        own24.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own24);

        board_24 = new BoardPanel();
        board_24.setBounds(430, 160, 50, 40);
        board_24.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_24.setBackground(new Color(19,168,87));
        contentPane.add(board_24);

        own23 = new BoardPanel();
        own23.setBounds(430, 110, 50, 10);
        own23.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own23);

        board_23 = new BoardPanel();
        board_23.setBounds(430, 110, 50, 40);
        board_23.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_23.setBackground(new Color(19,168,87));
        contentPane.add(board_23);

        own22 = new BoardPanel();
        own22.setBounds(430, 60, 50, 10);
        own22.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own22);

        board_22 = new BoardPanel();
        board_22.setBounds(430, 60, 50, 40);
        board_22.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_22.setBackground(new Color(19,168,87));
        contentPane.add(board_22);

        board_7 = new BoardPanel();
        board_7.setBounds(70, 310, 50, 40);
        board_7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(board_7);

        own6 = new BoardPanel();
        own6.setBounds(130, 310, 50, 10);
        own6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own6);

        board_6 = new BoardPanel();
        board_6.setBounds(130, 310, 50, 40);
        board_6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_6.setBackground(new Color(172,220,240));
        contentPane.add(board_6);

        own5 = new BoardPanel();
        own5.setBounds(190, 310, 50, 10);
        own5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own5);

        board_5 = new BoardPanel();
        board_5.setBounds(190, 310, 50, 40);
        board_5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_5.setBackground(new Color(172,220,240));
        contentPane.add(board_5);

        own4 = new BoardPanel();
        own4.setBounds(250, 310, 50, 10);
        own4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own4);

        board_4 = new BoardPanel();
        board_4.setBounds(250, 310, 50, 40);
        board_4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_4.setBackground(new Color(172,220,240));
        contentPane.add(board_4);

        own3 = new BoardPanel();
        own3.setBounds(310, 310, 50, 10);
        own3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own3);

        board_3 = new BoardPanel();
        board_3.setBounds(310, 310, 50, 40);
        board_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_3.setBackground(new Color(134,76,56));
        contentPane.add(board_3);

        own2 = new BoardPanel();
        own2.setBounds(370, 310, 50, 10);
        own2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(own2);

        board_2 = new BoardPanel();
        board_2.setBounds(370, 310, 50, 40);
        board_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        board_2.setBackground(new Color(134,76,56));
        contentPane.add(board_2);

        player_list = new JList();
        player_list.setBounds(10, 360, 470, 191);
        contentPane.add(player_list);

        panel_dice = new DicePanel.DicePanel();
        panel_dice.setBounds(250, 110, 70, 70);
        contentPane.add(panel_dice);

        panel_dice2 = new DicePanel.DicePanel();
        panel_dice2.setBounds(170, 110, 70, 70);
        contentPane.add(panel_dice2);

        btn_roll = new JButton("Roll");
        btn_roll.setBounds(590, 39, 93, 23);
        contentPane.add(btn_roll);

        btn_end_round = new JButton("End Round");
        btn_end_round.setBounds(590, 72, 93, 23);
        contentPane.add(btn_end_round);

        btn_surrender = new JButton("Surrender");
        btn_surrender.setBounds(590, 105, 93, 23);
        contentPane.add(btn_surrender);

        lblNewLabel = new JLabel("Action:");
        lblNewLabel.setBounds(526, 38, 54, 15);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Current Player:");
        lblNewLabel_1.setBounds(526, 10, 103, 15);
        contentPane.add(lblNewLabel_1);

        current_player = new JLabel("New label");
        current_player.setBounds(639, 20, 54, 15);
        contentPane.add(current_player);

        message_list = new JList();
        message_list.setBounds(490, 138, 284, 413);
        contentPane.add(message_list);
    }
}
