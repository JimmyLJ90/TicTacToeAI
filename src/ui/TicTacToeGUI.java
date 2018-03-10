package ui;

import game_control.Board;
import player.IPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicTacToeGUI implements UI  {

    private static TicTacToeGUI INSTANCE;
    private JFrame mainWindow;
    private GameBoard gb;

    public static TicTacToeGUI getINSTANCE()
    {
        if(INSTANCE == null)
            INSTANCE = new TicTacToeGUI();
        return INSTANCE;
    }

    private TicTacToeGUI()
    {
        mainWindow = new MainWindow();
    }

    @Override
    public List<IPlayer> choosePlayers() {
        StarterPageGUI sg = new StarterPageGUI();
        mainWindow.setContentPane(sg.$$$getRootComponent$$$());
        List<IPlayer> p = sg.getPlayerSelection();
        gb = new GameBoard();
        mainWindow.setContentPane(gb.$$$getRootComponent$$$());
        mainWindow.setVisible(true);
        return p;
    }

    @Override
    public void playersTurn(IPlayer currentPlayer , Board board , List<IPlayer> playerList) {
        gb.updateBoard(currentPlayer , board , playerList);
    }

    @Override
    public void humanPlayerTurn(Board board , int minimax) {
        gb.humanPlayersTurn(board , minimax);
    }
}
