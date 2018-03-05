package game_control;

import ui.TicTacToeGUI;
import ui.UI;

public class GameController {


    public static void main(String args[])
    {
        UI gui = new TicTacToeGUI();
        gui.choosePlayers();
    }
}
