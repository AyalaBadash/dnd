package Model.Unit.Player;

import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Unit.Enemy.Enemy;
import Model.Unit.HeroicUnit;
import Model.Unit.Unit;
import Model.Unit.Visitor;
import View.Turn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {

    protected int experience;
    protected int playerLevel;

    public Player(char tile, int x, int y) {
        super ( tile, x, y );
    }

    public void OnLevelUp(){
        playerLevel += 1;
        experience -= 50 * playerLevel;
        health.SetHealthPool(health.GetHealthPool() + 10 * playerLevel);
        health.SetHealthAmount(health.GetHealthPool());
        attackPoints += 4 * playerLevel;
        defensePoints += playerLevel;
    }

    public Turn OnPlayerTurn(char move, Tile[][] board){
        currBoard = board;
        Tile step;
        Point lastPosition = new Point ( position );
        switch (move){
            case 'w':
                step = board[position.x][position.y-1];
            case 'a':
                step = board[position.x-1][position.y];
            case 's':
                step = board[position.x][position.y + 1];
            case 'd':
                step = board[position.x+1][position.y];
            default:
                step = null;
        }
        if(step != null) {
            Turn turn = new Turn ( step.accept ( this ) );
            if (step.GetPosition ( ) == null) {
                board[position.x][position.y] = this;
                board[lastPosition.x][lastPosition.y] = new EmptyTile ( lastPosition.x, lastPosition.y );

            }
            return turn;
        }
        return new Turn ( "" );
    }


    protected List<Enemy> FindEnemies(int range, List<Enemy> enemies) {
        List<Enemy> closeEnemies = new ArrayList<> ();
        for ( Enemy e:enemies) {
            if(e.Range ( this ) < range)
                closeEnemies.add ( e );
        }
        return closeEnemies;
    }

    public String accept(Visitor visitor){
        return visitor.Visit ( this );
    }

    public String Visit(Enemy visited){
        String output = name + " engaged in combat with " + visited.GetName () + ".";
        output = output + "\n" + Describe ();
        output = output + "\n" + visited.Describe ();
        int attackRolled = Attack ();
        output = output + "\n" + name + " rolled " + attackRolled + " attack points.";
        int defenseRolled = visited.Defense ();
        output = output + "\n" + visited.GetName () + " rolled " + defenseRolled + " defense points.";
        output = output + "\n" + name + " dealt " + Damage (defenseRolled, attackRolled ) + " demage to " + visited.GetName ()+ ".";
        if(!visited.isAlive())
        {
            output = output + visited.Died () + " " + name + " gained " + visited.GetExperience () + " experience.";
            experience += visited.GetExperience ();
            Point temp = visited.GetPosition ();
            visited.SetPosition ( null );
            position = new Point ( temp );
        }
        return output;
    }

    public String Visit(Player visit){
        return "";
    }

    @Override
    public abstract String Describe();

    @Override
    public abstract Turn OnAbilityCast(List<Enemy> enemies);

    @Override
    public abstract void OnGameTick();

}
