package ui;

import game_control.GameController;
import player.AIFieldValue;
import player.HumanPlayer;
import player.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class StarterPageGUI {
    private JButton startGameButton;
    private JPanel MainPanel;
    private JPanel PlayerOnePanel;
    private JPanel PlayerTwoPanel;
    private JTextPane choosePlayerTypeTextPane1;
    private JTextPane choosePlayerTypeTextPane;
    private JTextPane gameTreeDepthTextPane;
    private JTextPane gameTreeDepthTextPane1;
    private JTextPane nameTextPane;
    private JTextArea pl1_name;
    private JTextPane colorTextPane;
    private JTextPane colorTextPane1;
    private JTextPane nameTextPane1;
    private JTextArea pl2_name;
    private JPanel pane12;
    private JPanel pane11;
    private JPanel pane13;
    private JPanel pane14;
    private JPanel pane21;
    private JPanel pane22;
    private JPanel pane23;
    private JPanel pane24;
    private JTextArea player1ChooseType;
    private MyMenu player1ChooseTypeMenu;
    private JTextArea player1ChooseDepth;
    private MyMenu player1ChooseDepthMenu;
    private JTextArea player1ChooseColor;
    private MyMenu player1ChooseColorMenu;
    private JTextArea player2ChooseType;
    private MyMenu player2ChooseTypeMenu;
    private JTextArea player2ChooseDepth;
    private MyMenu player2ChooseDepthMenu;
    private JTextArea player2ChooseColor;
    private MyMenu player2ChooseColorMenu;
    private Thread threadWaitingForPlayerSelection;

    private List<IPlayer> playerList;

    public StarterPageGUI() {
        $$$setupUI$$$();
        init();
    }

    private void init() {
        player1ChooseType.setComponentPopupMenu(player1ChooseTypeMenu);
        player1ChooseColor.setComponentPopupMenu(player1ChooseColorMenu);
        player2ChooseType.setComponentPopupMenu(player2ChooseTypeMenu);
        player2ChooseDepth.setComponentPopupMenu(player2ChooseDepthMenu);
        player2ChooseColor.setComponentPopupMenu(player2ChooseColorMenu);

        player1ChooseType.setText("Human");
        player1ChooseColor.setText("White");
        player2ChooseType.setText("AI Field Value");
        player2ChooseDepth.setText("3");
        player2ChooseColor.setText("Black");


        startGameButton.addActionListener(new Action() {
            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (pl1_name.getText().length() == 0 || pl2_name.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Give both players a name");
                    return;
                }
                List<IPlayer> players = new ArrayList<>(2);
                IPlayer player1, player2;
                ImageIcon player1Color, player2Color;
                if (player1ChooseColor.getText().equals("Black")) {
                    player1Color = new ImageIcon(new ImageIcon("black-dot.png").getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH));
                    player2Color = new ImageIcon(new ImageIcon("white-dot.png").getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH));
                    ;
                    ;
                } else {
                    player2Color = new ImageIcon(new ImageIcon("black-dot.png").getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH));
                    ;
                    ;
                    player1Color = new ImageIcon(new ImageIcon("white-dot.png").getImage().getScaledInstance(200, 130, Image.SCALE_SMOOTH));
                    ;
                    ;
                }

                if (player1ChooseType.getText().equals("Human"))
                    player1 = new HumanPlayer(pl1_name.getText(), player1Color, GameController.MAXIMIZER);
                else
                    player1 = new AIFieldValue(pl1_name.getText(), player1Color, GameController.MAXIMIZER, Integer.parseInt(player1ChooseDepth.getText()));

                if (player2ChooseType.getText().equals("Human"))
                    player2 = new HumanPlayer(pl2_name.getText(), player2Color, GameController.MINIMIZER);
                else
                    player2 = new AIFieldValue(pl2_name.getText(), player2Color, GameController.MINIMIZER, Integer.parseInt(player2ChooseDepth.getText()));

                players.add(player1);
                players.add(player2);
                playerList = players;
                threadWaitingForPlayerSelection.interrupt();


            }
        });
    }


    private void createUIComponents() {
        MainPanel = new JPanel();
        player1ChooseTypeMenu = new MyMenu("Player1Type");
        player1ChooseTypeMenu.add("Human");
        player1ChooseTypeMenu.add("AI Field Value");
        player1ChooseTypeMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                if (item.getText().equals("Human")) {
                    player1ChooseDepth.setText("");
                    player1ChooseDepth.setComponentPopupMenu(null);
                } else {
                    if (!player1ChooseType.getText().equals(item.getText()))
                        player1ChooseDepth.setText("3");
                    player1ChooseDepth.setComponentPopupMenu(player1ChooseDepthMenu);
                }
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
            }
        });
        player1ChooseDepthMenu = new MyMenu("Player1Depth");
        player1ChooseDepthMenu.add("3");
        player1ChooseDepthMenu.add("5");
        player1ChooseDepthMenu.add("7");
        player1ChooseDepthMenu.add("9");
        player1ChooseDepthMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
            }
        });
        player1ChooseColorMenu = new MyMenu("Player1Color");
        player1ChooseColorMenu.add("Black");
        player1ChooseColorMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
                String color = item.getText();
                if (color.equals("Black")) {
                    item.setText("White");
                    player2ChooseColor.setText("White");
                    ((JMenuItem) player2ChooseColorMenu.getComponent(0)).setText("Black");
                } else {
                    item.setText("Black");
                    player2ChooseColor.setText("Black");
                    ((JMenuItem) player2ChooseColorMenu.getComponent(0)).setText("White");
                }

            }
        });
        player2ChooseTypeMenu = new MyMenu("Player2Type");
        player2ChooseTypeMenu.add("Human");
        player2ChooseTypeMenu.add("AI Field Value");
        player2ChooseTypeMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                if (item.getText().equals("Human")) {
                    player2ChooseDepth.setText("");
                    player2ChooseDepth.setComponentPopupMenu(null);
                } else {
                    if (!player2ChooseType.getText().equals(item.getText()))
                        player2ChooseDepth.setText("3");
                    player2ChooseDepth.setComponentPopupMenu(player2ChooseDepthMenu);
                }
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
            }
        });


        player2ChooseDepthMenu = new MyMenu("Player2Depth");
        player2ChooseDepthMenu.add("3");
        player2ChooseDepthMenu.add("5");
        player2ChooseDepthMenu.add("7");
        player2ChooseDepthMenu.add("9");
        player2ChooseDepthMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
            }
        });
        player2ChooseColorMenu = new MyMenu("Player2Color");
        player2ChooseColorMenu.add("White");
        player2ChooseColorMenu.addMenuChildListeners(new MenuChildListener() {
            @Override
            public void onMenuItemClick(int i, JMenuItem item, JComponent parent) {
                (((JTextArea) ((JPopupMenu) parent).getInvoker())).setText(item.getText());
                String color = item.getText();
                if (color.equals("Black")) {
                    item.setText("White");
                    player1ChooseColor.setText("White");
                    ((JMenuItem) player1ChooseColorMenu.getComponent(0)).setText("Black");
                } else {
                    item.setText("Black");
                    player1ChooseColor.setText("Black");
                    ((JMenuItem) player1ChooseColorMenu.getComponent(0)).setText("White");
                }
            }
        });

    }


    public List<IPlayer> getPlayerSelection() {
        if (playerList != null)
            return playerList;
        else {
            try {
                threadWaitingForPlayerSelection = Thread.currentThread();
                while (true)
                    Thread.sleep(10000);

            } catch (InterruptedException e) {
                return playerList;
            }
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        MainPanel.setLayout(new GridBagLayout());
        PlayerOnePanel = new JPanel();
        PlayerOnePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        MainPanel.add(PlayerOnePanel, gbc);
        PlayerOnePanel.setBorder(BorderFactory.createTitledBorder("Player One:"));
        pane11 = new JPanel();
        pane11.setLayout(new GridBagLayout());
        pane11.setPreferredSize(new Dimension(114, 55));
        PlayerOnePanel.add(pane11);
        nameTextPane = new JTextPane();
        nameTextPane.setEditable(false);
        nameTextPane.setText("Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane11.add(nameTextPane, gbc);
        pl1_name = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane11.add(pl1_name, gbc);
        pane12 = new JPanel();
        pane12.setLayout(new GridBagLayout());
        PlayerOnePanel.add(pane12);
        choosePlayerTypeTextPane = new JTextPane();
        choosePlayerTypeTextPane.setEditable(false);
        choosePlayerTypeTextPane.setText("Choose Player Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane12.add(choosePlayerTypeTextPane, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane12.add(panel1, gbc);
        player1ChooseType = new JTextArea();
        player1ChooseType.setBackground(new Color(-855310));
        player1ChooseType.setEditable(false);
        panel1.add(player1ChooseType, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pane13 = new JPanel();
        pane13.setLayout(new GridBagLayout());
        PlayerOnePanel.add(pane13);
        gameTreeDepthTextPane = new JTextPane();
        gameTreeDepthTextPane.setEditable(false);
        gameTreeDepthTextPane.setText("Game Tree Depth");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane13.add(gameTreeDepthTextPane, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane13.add(panel2, gbc);
        player1ChooseDepth = new JTextArea();
        player1ChooseDepth.setBackground(new Color(-855310));
        player1ChooseDepth.setEditable(false);
        panel2.add(player1ChooseDepth, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pane14 = new JPanel();
        pane14.setLayout(new GridBagLayout());
        PlayerOnePanel.add(pane14);
        colorTextPane = new JTextPane();
        colorTextPane.setEditable(false);
        colorTextPane.setText("Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane14.add(colorTextPane, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane14.add(panel3, gbc);
        player1ChooseColor = new JTextArea();
        player1ChooseColor.setBackground(new Color(-855310));
        player1ChooseColor.setEditable(false);
        panel3.add(player1ChooseColor, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        PlayerTwoPanel = new JPanel();
        PlayerTwoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        MainPanel.add(PlayerTwoPanel, gbc);
        PlayerTwoPanel.setBorder(BorderFactory.createTitledBorder("Player Two:"));
        pane21 = new JPanel();
        pane21.setLayout(new GridBagLayout());
        pane21.setPreferredSize(new Dimension(114, 55));
        PlayerTwoPanel.add(pane21);
        nameTextPane1 = new JTextPane();
        nameTextPane1.setEditable(false);
        nameTextPane1.setText("Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane21.add(nameTextPane1, gbc);
        pl2_name = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane21.add(pl2_name, gbc);
        pane22 = new JPanel();
        pane22.setLayout(new GridBagLayout());
        PlayerTwoPanel.add(pane22);
        choosePlayerTypeTextPane1 = new JTextPane();
        choosePlayerTypeTextPane1.setEditable(false);
        choosePlayerTypeTextPane1.setText("Choose Player Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane22.add(choosePlayerTypeTextPane1, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane22.add(panel4, gbc);
        player2ChooseType = new JTextArea();
        player2ChooseType.setBackground(new Color(-855310));
        player2ChooseType.setEditable(false);
        player2ChooseType.setText("");
        panel4.add(player2ChooseType, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pane23 = new JPanel();
        pane23.setLayout(new GridBagLayout());
        PlayerTwoPanel.add(pane23);
        gameTreeDepthTextPane1 = new JTextPane();
        gameTreeDepthTextPane1.setEditable(false);
        gameTreeDepthTextPane1.setEnabled(true);
        gameTreeDepthTextPane1.setText("Game Tree Depth");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane23.add(gameTreeDepthTextPane1, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane23.add(panel5, gbc);
        player2ChooseDepth = new JTextArea();
        player2ChooseDepth.setBackground(new Color(-855310));
        player2ChooseDepth.setEditable(false);
        panel5.add(player2ChooseDepth, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pane24 = new JPanel();
        pane24.setLayout(new GridBagLayout());
        PlayerTwoPanel.add(pane24);
        colorTextPane1 = new JTextPane();
        colorTextPane1.setEditable(false);
        colorTextPane1.setText("Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pane24.add(colorTextPane1, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane24.add(panel6, gbc);
        player2ChooseColor = new JTextArea();
        player2ChooseColor.setBackground(new Color(-855310));
        player2ChooseColor.setEditable(false);
        panel6.add(player2ChooseColor, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        startGameButton = new JButton();
        startGameButton.setText("Start Game");
        startGameButton.setVerticalAlignment(0);
        startGameButton.setVerticalTextPosition(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MainPanel.add(startGameButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}
