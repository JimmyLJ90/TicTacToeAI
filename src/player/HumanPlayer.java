package player;

import game_control.Board;
import ui.TicTacToeGUI;

import javax.swing.*;
import java.awt.*;

public class HumanPlayer implements IPlayer {

    private String name;
    private ImageIcon color;
    private int minimax;
    @Override
    public Board makeMove(Board currentBoardState) {
        TicTacToeGUI.getINSTANCE().humanPlayerTurn(currentBoardState , minimax);
        return currentBoardState;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int minimax() {
        return minimax;
    }

    @Override
    public ImageIcon color() {
        return color;
    }

    public HumanPlayer(String name , ImageIcon color , int minimax)
    {
        this.name = name;
        this.color = color;
        this.minimax = minimax;
    }
}
