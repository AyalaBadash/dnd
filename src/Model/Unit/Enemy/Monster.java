package Model.Unit.Enemy;
import Model.Tile.Tile;
import View.GameBoard;
import View.Turn;

import java.awt.*;
import java.util.Random;

public class Monster extends Enemy {

    private int visionRange;

    public Monster (int x, int y, Minions m){
        super(m.tile, x, y);
        name = m.name;
        health = m.health;
        attackPoints = m.attack;
        defensePoints = m.defense;
        visionRange = m.visionRange;
        experienceValue = m.experienceValue;
    }
    @Override
    public void OnGameTick() {}

    @Override
    public Turn OnEnemyTurn(GameBoard board) {
        String output = "";
        Tile[][] currBoard = board.GetBoard ();
        Point lastPosition = new Point(position);
        if(Range ( board.GetPlayer () ) <= visionRange)
        {
            int dx = position.x - board.GetPlayer ().GetPosition ().x;
            int dy = position.y - board.GetPlayer ().GetPosition ().y;
            if(Math.abs ( dx ) > Math.abs ( dy ))
                if(dx > 0)
                    output = MoveLeft ( board);
                else
                    output = MoveRight ( board );
            else
                if (dy > 0)
                    output = MoveUp ( board );
                else
                    output = MoveDown ( board );
        }
        else
            output = RandomMove (board);
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

    private String RandomMove(GameBoard board) {
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
        String output = name;
        for(int i = name.length (); i <= 25; i++)
            output += " ";
        String healthString = "Health: " + health.GetHealthAmount ()+"/" + health.GetHealthPool ();
        output += healthString;
        for(int i = healthString.length (); i <= 25; i++)
            output += " ";
        String attackString = "Attack: "+ attackPoints;
        output += attackString;
        for(int i = attackString.length (); i <= 25; i++)
            output += " ";
        String defenseString = "Defense: "+ defensePoints;
        output += defenseString;
        for(int i = defenseString.length (); i <= 25; i++)
            output += " ";
        String experienceString = "Experience: "+ experienceValue;
        output += experienceString;
        for(int i = experienceString.length (); i <= 25; i++)
            output += " ";
        String visionRangeString = "Vision Range: "+ visionRange;
        output += visionRangeString;
        return output;
    }

}
