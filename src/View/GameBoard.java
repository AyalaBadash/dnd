package View;

import Model.Tile.Tile;
import Model.Unit.Player.Player;

public class GameBoard implements Printer {
    Tile[][] board;
    Player currPlayer;

    @Override
    public void Print() {
        for(int i = 0; i < board.length; i++) {
            for ( int j = 0 ; j < board[i].length ; j++ )
                System.out.print ( board[i][j].toString () );
            System.out.println ( );
        }
    }

    public Tile[][] GetBoard(){ return board;}
    public Player GetPlayer(){return currPlayer;}
    public void SetBoard(Tile[][] board){this.board = board;}
    public void SetPlayer(Player player){currPlayer = player;}
}
