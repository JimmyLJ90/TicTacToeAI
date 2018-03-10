package game_control;

import player.IPlayer;
import ui.TicTacToeGUI;
import ui.UI;

import javax.swing.*;
import java.util.List;

public class GameController {

    public static final int MINIMIZER = -1;
    public static final int MAXIMIZER = 1;

    public static final int[][] POSITION_TO_BIT_VALUE = {{1 , 8 , 64},
                                                         {2 , 16 , 128},
                                                         {4 , 32 , 256}};


    private List<IPlayer> playerList;
    private UI gui;
    private Board board;
    private int playersTurn;
    public static void main(String args[])
    {
        GameController gameController = new GameController();
        gameController.setUpGame();
    }

    public void setUpGame()
    {
        gui = TicTacToeGUI.getINSTANCE();
        while(true)
        {
            playerList = gui.choosePlayers();
            board = new Board();
            playersTurn = 0;
            gameloop();
        }


    }

    public void gameloop()
    {
        IPlayer currentPlayer = playerList.get(0);
        int rounds = 0;
        boolean tie = false;
        while(!playerWon())
        {
            currentPlayer = playerList.get(playersTurn);
            gui.playersTurn(currentPlayer , board , playerList);
            board = currentPlayer.makeMove(new Board(board.board));
            playersTurn = (playersTurn+1)%2;
            rounds++;
            if(rounds == 9 && !playerWon())
            {
                tie = true;
                break;
            }
        }
        
        gui.playersTurn(currentPlayer , board , playerList);
        if(!tie)
            JOptionPane.showMessageDialog(null, currentPlayer.getName()+ " won the game!");
        else
            JOptionPane.showMessageDialog(null, "Both players lost :(");

    }

    public boolean playerWon()
    {

        int occupy = board.board >> 9;
        //check rows
        for(int i = 0 ; i<3 ; i++)
          if(!(((board.board>>(3*i))&7)==7))
              continue;
          else if(((((occupy >> (3 * i))&7 ) == 7)) || (((occupy >> (3 * i)) & 7) == 0))
              return true;

        //Check columns
        for(int i = 0 ; i<3 ; i++)
        {
            if(!((( board.board >> i) & 73 ) == 73))
                continue;
            else if((((occupy >> i) & 73) == 73) || (((occupy >> i) & 73) == 0))
                return true;
        }
        //Check diagonals
        boolean dia1 = (((board.board & 273 )== 273) && (((occupy & 273) == 273) || ((occupy & 273) == 0))) ;
        boolean dia2 = (((board.board & 84 )== 84) && (((occupy & 84) == 84) || ((occupy & 84) == 0)));

        if(dia1 || dia2)
            return true;
        return false;
    }
}
