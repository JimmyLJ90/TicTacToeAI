package player;

import game_control.Board;
import game_control.PlayerMove;

public interface IPlayer {

    PlayerMove makeMove(Board currentBoardState);
}
