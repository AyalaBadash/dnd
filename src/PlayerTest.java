import Model.Helpers.Cooldown;
import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Wall;
import Model.Unit.Enemy.*;
import Model.Unit.Player.Player;
import Model.Unit.Player.Warrior;
import Model.Unit.Player.Warriors;
import View.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Tile[][] board;
    Warrior toTest;
    Minion m;

    @BeforeEach
    void setUp() {
        toTest = new Warrior ( 1, 4, Warriors.JonSnow );
        m = new Minion ( 4, 4, Minions.QueensGuard );
        board = new Tile[7][7];
        for ( int i = 0 ; i < 7 ; i++ ) {
            for ( int j = 0 ; j < 7 ; j++ ) {
                if (i == 0 | j == 0)
                    board[i][j] = new Wall ( i, j );
                else if (i == toTest.GetPosition ( ).x & j == toTest.GetPosition ( ).y)
                    board[i][j] = toTest;
                else if (i == m.GetPosition ( ).x & j == m.GetPosition ( ).y)
                    board[i][j] = m;
                else
                    board[i][j] = new EmptyTile ( i, j );
            }
        }
    }

    @Test
    void afterAttack() {
        toTest.AfterAttack ( 200 );
        assertEquals ( 100, toTest.getHealth ().GetHealthAmount (), "health amount should be 100" );
    }

    @Test
    void onLevelUp() {
        toTest.OnLevelUp ();
        Cooldown c = toTest.getCooldown ();
        assertEquals ( 2, toTest.getPlayerLevel (), "should level up to 2." );
        assertEquals ( 0, c.GetRemainingCooldown (), "after level up should be 0." );
        assertEquals ( 330, toTest.getHealth ().GetHealthPool (), "after level up should be 330.");
        assertEquals ( 42, toTest.getAttackPoints (), "attack points should be 42 after level up to 2." );
        assertEquals ( 8, toTest.getDefensePoints (), "defense points should be 8 after level up tp 2." );
        assertEquals ( 330, toTest.getHealth ().GetHealthAmount (), "health amount should be health pool." );
    }

    @Test
    void onPlayerTurn() {
        Level l = new Level ( toTest );
        List<String> toLevel = new ArrayList<> (  );
        toLevel.add ( "#######" );
        toLevel.add ( "#..@..#" );
        toLevel.add ( "#.....#" );
        toLevel.add ( "#.....#" );
        toLevel.add ( "#.....#" );
        toLevel.add ( "#.....#" );
        toLevel.add ( "#######" );
        l.CreateBoardToLevel ( toLevel );
        toTest.OnPlayerTurn ('d', l);
        assertEquals ( 4, toTest.GetPosition ().x, "should move right." );
        toTest.OnPlayerTurn ('a', l);
        assertEquals ( 3, toTest.GetPosition ().x, "should move left." );
        toTest.OnPlayerTurn ('w', l);
        assertEquals ( 1, toTest.GetPosition ().y, "should stay in place." );
    }

    @Test
    void findEnemies() {
        Minion m1 = new Minion ( 1,2, Minions.QueensGuard );
        Minion m2 = new Minion ( 5,6, Minions.WhiteWalker );
        List<Enemy> e1 = new ArrayList<> (  );
        e1.add ( m1 );
        e1.add ( m2 );
        board[1][2] = m1;
        board[5][6] = m2;
        List<Enemy> e = toTest.FindEnemies(3, e1);
        assertEquals ( 1,e.size (), "should find 1 enemy." );
    }

    @Test
    void updateExperience() {
        toTest.UpdateExperience ( 20 );
        assertEquals ( 20, toTest.getExperience (), "should be updated to 20." );
    }

    @Test
    void dead() {
        toTest.Dead ();
        assertFalse ( toTest.isAlive (), "should be false." );
        assertEquals ( 'X', toTest.toString ().charAt ( 0 ), "should be changed to 'X'." );
    }

    @Test
    void onGameTick() {
        Cooldown c= toTest.getCooldown ();
        c.SetRemainingCooldown ( 3 );
        toTest.OnGameTick ();
        assertEquals ( 2, c.GetRemainingCooldown (), "should be down to 2." );
    }
}