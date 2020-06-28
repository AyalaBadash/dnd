package Model.Unit.Enemy;
import Model.Helpers.Health;
import Model.Tile.Tile;
import View.GameBoard;
import View.Level;
import View.Turn;

import java.awt.*;
import java.util.Random;

public class Monster extends Enemy {

    protected int visionRange;

    public Monster (char tile, int x, int y){
        super(tile, x, y);
    }
    @Override
    public void OnGameTick() {}

    @Override
    public Turn OnEnemyTurn(Level level) {
        String output = "";
        Tile[][] currBoard = level.GetBoard ().GetBoard ();
        Point lastPosition = new Point(position);
        if(Range ( level.getCurrPlayer () ) < visionRange)
        {
            int dx = position.x - level.getCurrPlayer ().GetPosition ().x;
            int dy = position.y - level.getCurrPlayer ().GetPosition ().y;
            if(Math.abs ( dx ) > Math.abs ( dy ))
                if(dx > 0)
                    output = MoveLeft ( level.GetBoard ());
                else
                    output = MoveRight ( level.GetBoard () );
            else
                if (dy > 0)
                    output = MoveUp ( level.GetBoard () );
                else
                    output = MoveDown ( level.GetBoard () );
        }
        else
            output = RandomMove (level.GetBoard ());
        Turn turn = new Turn (output);
        return turn;
    }

    private String MoveLeft(GameBoard board){
        Point lastPosition = new Point ( position );
        String output =  board.GetBoard ()[position.x - 1][position.y].accept ( this );
        FixBoard ( board.GetBoard (),lastPosition, board.GetBoard ()[position.x][position.y]);
        return output;
    }
     private String MoveRight(GameBoard board){
         Point lastPosition = new Point ( position );
         String output = board.GetBoard ()[position.x + 1][position.y].accept ( this );
         FixBoard ( board.GetBoard (),lastPosition, board.GetBoard ()[position.x][position.y]);
         return output;
    }

    private String MoveUp(GameBoard board){
        Point lastPosition = new Point ( position );
        String output =  board.GetBoard ()[position.x ][position.y - 1].accept ( this );
        FixBoard ( board.GetBoard (),lastPosition, board.GetBoard ()[position.x][position.y]);
        return output;
    }

    private String MoveDown(GameBoard board){
        Point lastPosition = new Point ( position );
        String output = board.GetBoard ()[position.x][position.y + 1].accept ( this );
        FixBoard ( board.GetBoard (),lastPosition, board.GetBoard ()[position.x][position.y]);
        return output;
    }

    protected String RandomMove(GameBoard board) {
        Random random = new Random ( );
        int movement = random.nextInt ( 3 );
        switch (movement) {
            case (0):
                return MoveLeft ( board );
            case (1):
                return MoveRight ( board );
            case (2):
                return MoveUp ( board );
            default:
                return MoveDown ( board );
        }
    }

    private void FixBoard(Tile[][] board, Point lastPosition, Tile moveTo){
        if(!position.equals ( lastPosition )) {
            board[lastPosition.x][lastPosition.y] = moveTo;
            board[position.x][position.y] = this;
        }
    }
    @Override
    public String Describe(){
        String output = super.Describe ();
        String visionRangeString = "Vision Range: "+ visionRange;
        output += visionRangeString;
        return output;
    }

}
