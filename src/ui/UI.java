package ui;

import game_control.Board;
import player.IPlayer;

import java.util.List;

public interface UI {

    List<IPlayer> choosePlayers();
    void playersTurn(IPlayer player , Board board , List<IPlayer> playerList);
    void humanPlayerTurn(Board board , int minimax);

}
