package game_control;

import java.util.Random;

public class BestChildNodeList {

    private int nNodes = 0;
    private Board[] nodes = new Board[9];
    private int bestVal;
    private int minimax;

    public BestChildNodeList(int minimax)
    {
        bestVal = -1*minimax*200000;
        this.minimax = minimax;
    }

    public void insert(Board childNode , int val)
    {
        if(minimax < 0)
        {
            if(val> bestVal)
                return;
            else if(val == bestVal)
                nodes[nNodes++] = childNode;
            else{
                nNodes = 1;
                bestVal = val;
                nodes[0] = childNode;
            }
        }
        else
        {
            if(val < bestVal)
                return;
            else if(val == bestVal)
                nodes[nNodes++] = childNode;
            else
            {
                nNodes = 1;
                bestVal = val;
                nodes[0] = childNode;
            }
        }


    }

    public Board getRandomBestChild()
    {
        return nodes[new Random().nextInt(nNodes)];
    }

    public Board[] getBestChildNodes()
    {
        Board[] b = new Board[nNodes];
        for(int i = 0 ; i<nNodes ; i++)
        {
            b[i] = nodes[i];
        }
        return b;
    }

}
