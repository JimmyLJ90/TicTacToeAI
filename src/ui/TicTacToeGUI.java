package ui;

import player.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicTacToeGUI extends JFrame implements UI  {

    public TicTacToeGUI()
    {
        Dimension d = new Dimension();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.height = 480;
        d.width = 640;
        setSize(d);
        setVisible(true);
    }

    @Override
    public List<IPlayer> choosePlayers() {
        StarterPageGUI sg = new StarterPageGUI();
        setContentPane(sg.$$$getRootComponent$$$());
        return null;
    }

    @Override
    public void playersTurn(IPlayer player) {

    }
}
