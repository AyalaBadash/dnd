package Controller;

import Model.Tile.Tile;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;
import View.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private GameBoard board;
    private List<Enemy> enemies;
    private char player;
    private Player currPlayer;

    private PlayerCreator pc = new PlayerCreator();
    private EnemyCreator ec = new EnemyCreator ();
    private WallCreator wc = new WallCreator ();
    private EmptyCreator emc = new EmptyCreator ();

    public Level (char currPlayer){
        player = currPlayer;
        enemies = new ArrayList<> ();
        board = new GameBoard ();
    }

    public Player CreateBoardToLevel(List<String> toCreate){
        int out = toCreate.get ( 0 ).length ();
        int in = toCreate.size ();
        Tile[][] table = new Tile[in][out];
        for(int i = 0; i < in; i++){
            for ( int j = 0; j < out; j++ ){
                char curr = toCreate.get ( i ).charAt ( j );
                if( curr == '.')
                    table[i][j] = emc.Create ( curr, i, j);
                else if ( curr == '#' )
                    table[i][j] = wc.Create ( curr, i, j );
                else if( curr == '@') {
                    currPlayer = (Player) pc.Create ( player, i, j );
                    table[i][j] = currPlayer;
                }
                else
                {
                    Enemy toAdd = (Enemy) ec.Create ( curr, i, j );
                    table[i][j] = toAdd;
                    enemies.add ( toAdd );
                }
            }
        }
        board.SetBoard(table);
        board.SetPlayer ( currPlayer );
        return currPlayer;
    }

    public GameBoard GetBoard(){return board;}
    public List<Enemy> GetEnemies(){return enemies;}

    public boolean isStillOn() {
        return currPlayer.isAlive ( ) & enemies.size ( ) > 0;
    }

}
