package ui;

import player.IPlayer;

import java.util.List;

public interface UI {

    List<IPlayer> choosePlayers();
    void playersTurn(IPlayer player);

}
