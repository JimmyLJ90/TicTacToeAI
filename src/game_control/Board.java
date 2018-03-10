package game_control;

public class Board {
    public int board;
    private int childNodeIterator = 9;

    public Board(int board)
    {
        this.board = board;
    }

    public Board()
    {
        this.board = 0;
    }

    public int valAt(int x , int y)
    {
        int shift = x+3*y;
        int tmp1 = (board & GameController.POSITION_TO_BIT_VALUE[x][y]) >> (shift);
        int tmp2 = (board >> (9+shift)) & 1;
        int tmp3 = tmp2*2-1;
        return tmp3 * tmp1;
    }

    public void putVal(int x , int y , int minimax)
    {
        board = board | GameController.POSITION_TO_BIT_VALUE[x][y];
        board = board | (((minimax+1) / 2) << (9+x+3*y));

    }

    public Board nextChildNode(int minimax)
    {
        int newBoard = board;
        while(childNodeIterator!=-0)
        {
            --childNodeIterator;
            int newPiece = 1 << childNodeIterator;
            if((newPiece & newBoard) == 0)
            {
                int newPieceMinimax = ((minimax+1)/2) << (9+childNodeIterator);
                newBoard = newBoard | newPiece;
                newBoard = newBoard | newPieceMinimax;
                return new Board(newBoard);
            }

        }
        return null;

    }

    public String toString()
    {
        String result = "";
        for(int i = 0 ; i<3 ; i++)
        {
            result += "|---|---|---|\n|";
            for(int j = 0 ; j<3 ; j++)
            {
                int v = valAt(j , i);
                char vc = v == 0 ? ' ' : v == 1 ? 'O' : 'X';
                result += " " +vc+" |";
            }
            result+="\n";
        }
        result += "|---|---|---|\n";
        return result;
    }


}
