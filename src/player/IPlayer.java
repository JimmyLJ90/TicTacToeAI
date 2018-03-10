package player;

import game_control.Board;

import javax.swing.*;

public interface IPlayer {

    Board makeMove(Board currentBoardState);
    String getName();
    int minimax();
    ImageIcon color();

}
