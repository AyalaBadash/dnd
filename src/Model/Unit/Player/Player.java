package Model.Unit.Player;

import View.GameBoard;
import View.Level;
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
import java.util.Random;

public abstract class Player extends Unit implements HeroicUnit {

    protected int experience;
    protected int playerLevel;

    public Player(char tile, int x, int y) {
        super ( tile, x, y );
    }

    public String OnLevelUp(){
        String output= "\n";
        playerLevel += 1;
        output = output + name + " reached level " + playerLevel + ": ";
        experience -= 50 * playerLevel;
        health.SetHealthPool(health.GetHealthPool() + 10 * playerLevel);
        output = output + "+" + 10*playerLevel + " Health, ";
        health.SetHealthAmount(health.GetHealthPool());
        attackPoints += 4 * playerLevel;
        defensePoints += playerLevel;
        return output;
    }

    public Turn OnPlayerTurn(char move, Level level){
        Tile[][] currBoard = level.GetBoard ().GetBoard ();
        Tile step;
        Point lastPosition = new Point ( position );
        switch (move){
            case 'w':
                step = currBoard[position.x][position.y-1];
                break;
            case 'a':
                step = currBoard[position.x-1][position.y];
                break;
            case 's':
                step = currBoard[position.x][position.y + 1];
                break;
            case 'd':
                step = currBoard[position.x+1][position.y];
                break;
            case 'e':
                return OnAbilityCast ( level );
            default:
                step = null;
                break;
        }
        if(step != null) {
            Turn turn = new Turn ( step.accept ( this ) );
            if(!lastPosition.equals ( position )) {
                currBoard[position.x][position.y] = this;
                currBoard[lastPosition.x][lastPosition.y] = step;
            }
            if (step.GetPosition ( ) == null) {
                currBoard[position.x][position.y] = this;
                currBoard[lastPosition.x][lastPosition.y] = new EmptyTile ( lastPosition.x, lastPosition.y );
                level.RemoveEnemy();
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
        int damage = Damage (defenseRolled, attackRolled );
        output = output + "\n" + name + " dealt " + damage + " damage to " + visited.GetName ()+ ".";
        visited.AfterAttack(damage);
        if(!visited.isAlive())
        {
            output = output + visited.GetName () + " died. " + name + " gained " + visited.GetExperience () + " experience.";
            output = output + UpdateExperience ( visited.GetExperience () ) ;
            Point temp = visited.GetPosition ();
            visited.SetPosition ( null );
            position = new Point ( temp );
        }
        return output;
    }

    public Enemy RandomEnemyInRange(int range, List<Enemy> enemies){
        Enemy enemy = null;
        List<Enemy> enemiesInRange = FindEnemies ( range, enemies );
        if(enemiesInRange.size () == 0)
            return enemy;
        Random random = new Random ();
        int randomEnemy = random.nextInt ( enemiesInRange.size () - 1);
        enemy = enemiesInRange.get ( randomEnemy );
        return enemy;
    }

    public String UpdateExperience(int add){
        experience = experience + add;
        if(experience >= playerLevel * 50)
        {
            return  OnLevelUp ();
        }
        else return "";
    }

    public String Visit(Player visited){
        return "";
    }

    public void Dead(){
        isAlive = false;
        tile = 'X';
    }

    @Override
    public abstract String Describe();

    @Override
    public abstract Turn OnAbilityCast(Level level);

    @Override
    public abstract void OnGameTick();

}
