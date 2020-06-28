package View;

import Controller.EmptyCreator;
import Controller.EnemyCreator;
import Controller.PlayerCreator;
import Controller.WallCreator;
import Model.Tile.Tile;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level implements Printer{
    private GameBoard board;
    private List<Enemy> enemies;
    private Player currPlayer;
    private Point playerStartPosition;

    private PlayerCreator pc = new PlayerCreator();
    private EnemyCreator ec = new EnemyCreator ();
    private WallCreator wc = new WallCreator ();
    private EmptyCreator emc = new EmptyCreator ();

    public Level (Player currPlayer){
        this.currPlayer = currPlayer;
        enemies = new ArrayList<> ();
        board = new GameBoard ();
    }

    //creates the board from the string's list from the file
    public Player CreateBoardToLevel(List<String> toCreate){
        int width = toCreate.size ();
        int length = toCreate.get (0).length ();
        Tile[][] table = new Tile[length][width];
        for(int i = 0; i < width; i++){
            for ( int j = 0; j < length; j++ ){
                char curr = toCreate.get ( i ).charAt ( j );
                if( curr == '.')
                    table[j][i] = emc.Create ( curr, j, i);
                else if ( curr == '#' )
                    table[j][i] = wc.Create ( curr, j, i );
                else if( curr == '@') {
                    currPlayer.SetPosition ( new Point ( j,i ) );
                    table[j][i] = currPlayer;
                    playerStartPosition = new Point ( j,i );
                }
                else
                {
                    Enemy toAdd = (Enemy) ec.Create ( curr, j, i );
                    table[j][i] = toAdd;
                    enemies.add ( toAdd );
                }
            }
        }
        board.SetBoard(table);
        return currPlayer;
    }

    //checks each round if the level needs to over
    public boolean isStillOn() {
        return currPlayer.isAlive ( ) & enemies.size ( ) > 0;
    }

    @Override
    public void Print() {
        board.Print ();
        System.out.println (currPlayer.Describe ());
    }

    public Point getPlayerStartPosition() {
        return playerStartPosition;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public GameBoard GetBoard(){return board;}

    public List<Enemy> GetEnemies(){return enemies;}
}
