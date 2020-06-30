import Model.Helpers.Visibility;
import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Wall;
import Model.Unit.Enemy.Trap;
import Model.Unit.Enemy.Traps;
import Model.Unit.Player.Player;
import Model.Unit.Player.Warrior;
import Model.Unit.Player.Warriors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    Tile[][] board;
    Player p;
    Trap toTest;

    @BeforeEach
    void setUp() {
        p = new Warrior ( 1, 4, Warriors.JonSnow );
        toTest = new Trap ( 4, 4, Traps.QueensTrap );
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
    void dead() {
        toTest.Dead ();
        assertFalse ( toTest.isAlive (), "Should be false." );
    }

    @Test
    void isAlive() {
        assertTrue ( toTest.isAlive (), "Should be true." );
        toTest.AfterAttack ( 250 );
        assertFalse ( toTest.isAlive (), "After attack stronger than health should be false." );
    }

    @Test
    void Switch() {
        int x = p.GetPosition ().x;
        int y = p.GetPosition ().y;
        toTest.Switch ( p );
        assertEquals ( x, toTest.GetPosition ().x, "x posiotion expected is 1." );
    }

    @Test
    void afterAttack() {
        toTest.AfterAttack ( 50 );
        assertEquals ( 200, toTest.getHealth ().GetHealthAmount (), "after attack of 50 points - shpuld be 200 health amount" );
    }

    @Test
    void range() {
        assertEquals ( 3, toTest.Range ( p ), "range should be 3" );
    }

    @Test
    void onGameTick() {
        Visibility v = toTest.getVisibility ();
        toTest.OnGameTick ();
        assertEquals ( 1, v.GetTickCount (), "tick count should be updated to 1" );
        for(int i = 1; i < v.GetInvisibilityTime () + v.GetVisibilityTime (); i ++)
            toTest.OnGameTick ();
        assertEquals ( 0, v.GetTickCount (), "tick count should be 0 after visibilityTime and invisibility game ticks." );
    }


    @Test
    void testToString() {
        assertEquals ( 'Q', toTest.toString ().charAt ( 0 ), "Should be visible first and show 'Q'." );
        Visibility v = toTest.getVisibility ();
        for(int i = 0; i <= v.GetVisibilityTime (); i++)
            toTest.OnGameTick ();
        assertEquals ( '.', toTest.toString ().charAt ( 0 ), "Should be invisible after visibility time and show '.'." );
    }
}