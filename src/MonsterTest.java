import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Wall;
import Model.Unit.Enemy.Minion;
import Model.Unit.Enemy.Minions;
import Model.Unit.Enemy.Trap;
import Model.Unit.Enemy.Traps;
import Model.Unit.Player.Player;
import Model.Unit.Player.Warrior;
import Model.Unit.Player.Warriors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    Tile[][] board;
    Player p;
    Minion toTest;

    @BeforeEach
    void setUp() {
        p = new Warrior ( 1, 4, Warriors.JonSnow );
        toTest = new Minion ( 4, 4, Minions.QueensGuard );
        board = new Tile[7][7];
        for ( int i = 0 ; i < 7 ; i++ ) {
            for ( int j = 0 ; j < 7 ; j++ ) {
                if (i == 0 | j == 0)
                    board[i][j] = new Wall ( i, j );
                else if (i == p.GetPosition ( ).x & j == p.GetPosition ( ).y)
                    board[i][j] = p;
                else if (i == toTest.GetPosition ( ).x & j == toTest.GetPosition ( ).y)
                    board[i][j] = toTest;
                else
                    board[i][j] = new EmptyTile ( i, j );
            }
        }
    }

    @Test
    void isAlive() {
        assertTrue ( toTest.isAlive (), "should be alive first." );
        toTest.AfterAttack ( 10000 );
        assertFalse ( toTest.isAlive (), "should be dead after attack with 10000 damage." );
    }
}