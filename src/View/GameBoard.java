package View;

import Model.Tile.Tile;

public class GameBoard implements Printer {
    Tile[][] board;

    @Override
    public void Print() {
        for(int i = 0; i < board[0].length; i++) {
            for ( int j = 0 ; j < board.length ; j++ )
                System.out.print ( board[j][i].toString () );
            System.out.println ( );
        }
    }


    public Tile[][] GetBoard(){ return board;}
    public void SetBoard(Tile[][] board){this.board = board;}
}
