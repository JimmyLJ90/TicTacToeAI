package game_control;

public class Board {
    public static final char PLAYER_ONE_SYMBOL = 'O';
    public static final char PLAYER_TWO_SYMBOL = 'X';
    private char[][] board = new char[3][3];

    public char getPiece(int x , int y)
    {
        return board[x][y];
    }

    public boolean putPiece(PlayerMove pm)
    {
        if(board[pm.x][pm.y] != 0)
            return false;
        board[pm.x][pm.y] = pm.playerSymbol;
        return true;
    }

}
