package monopoly;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ConfigureUI extends JDialog {
    private JTextField u_board_config;
    private JButton u_btn_browse;
    private JComboBox u_dollar_sign;
    private JButton u_launch;
    private JButton u_cancel;
    private ConfigureUI self;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ConfigureUI dialog = new ConfigureUI();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ConfigureUI() {
        self = this;
        setTitle("Monopoly Launcher");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                u_launch = new JButton("Launch");
                u_launch.setActionCommand("OK");
                buttonPane.add(u_launch);
                getRootPane().setDefaultButton(u_launch);
            }
            {
                u_cancel = new JButton("Cancel");
                u_cancel.setActionCommand("Cancel");
                buttonPane.add(u_cancel);
            }
        }
        {
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            {
                JLabel lblNewLabel = new JLabel("Board Config:");
                lblNewLabel.setBounds(10, 10, 80, 15);
                panel.add(lblNewLabel);
            }

            u_board_config = new JTextField();
            u_board_config.setBounds(100, 7, 221, 21);
            panel.add(u_board_config);
            u_board_config.setColumns(10);
            u_board_config.setText("BoardConfig.json");

            u_btn_browse = new JButton("Browse..");
            u_btn_browse.setBounds(331, 6, 93, 23);
            panel.add(u_btn_browse);
            u_btn_browse.addActionListener(
                    new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            String config_file_path = DialogProvider.getInstance().show_file_dialog(self, ".");
                            if(config_file_path != null) {
                                Config.configurable_board_config_path = config_file_path;
                            }
                        }
                    }
            );


            u_dollar_sign = new JComboBox();
            u_dollar_sign.setModel(new DefaultComboBoxModel(new String[] {"United States - $", "Europe - \u20AC", "Britain - \u00A3", "France - \u20A3", "Japan - \u00A5"}));
            u_dollar_sign.setBounds(100, 31, 221, 23);
            panel.add(u_dollar_sign);

            u_dollar_sign.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    if(ie.getStateChange() == ItemEvent.SELECTED) {
                        String item = (String)ie.getItem();
                        System.err.println("item: " + item + " | "+ item.split(" - ")[1]);
                        Config.configurable_dollar_sign = item.split(" - ")[1];
                    }
                }
            });

            u_launch.addActionListener(
                    new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            String config_text = u_board_config.getText();
                            if(config_text != null){
                                if(config_text.length() > 0) {
                                    Config.configurable_board_config_path = config_text;
                                }
                            }

                            String dollar_sign = (String)u_dollar_sign.getSelectedItem();
                            Config.configurable_dollar_sign = dollar_sign.split(" - ")[1];

                            EventQueue.invokeLater(() -> {
                                try {
                                    MonopolyGUI frame = new MonopolyGUI();
                                    frame.setVisible(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            dispose();

                        }
                    }
            );

            u_cancel.addActionListener(
                    new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            System.exit(0);
                        }
                    }
            );



            JLabel lblNewLabel_1 = new JLabel("Dollar Sign:");
            lblNewLabel_1.setBounds(10, 35, 80, 15);
            panel.add(lblNewLabel_1);
        }
    }
}