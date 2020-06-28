package Model.Unit.Player;

import Model.Tile.EmptyTile;
import Model.Unit.Enemy.Enemy;
import View.Level;
import View.Turn;

import java.util.List;

public class Hunter extends Player {

    int range;
    int arrowsCount;
    int ticksCount;

    public Hunter(int x, int y, Hunters h) {
        super ( h.tile, x, y );
        name = h.name;
        health = h.health;
        attackPoints = h.attackPoints;
        defensePoints = h.defensePoints;
        range = h.range;
        playerLevel = 1;
        arrowsCount = playerLevel * 10;
        ticksCount = 0;
    }

    @Override
    public String Describe() {
        String output = super.Describe ();
        String arrowString = "Arrows: " + arrowsCount;
        output += arrowString;
        for(int i = arrowString.length (); i <= 25; i++)
            output += " ";
        String rangeString = "Range: " + range;
        return output;
    }

    @Override
    public String OnLevelUp(){
        String output = "";
        output = super.OnLevelUp ();
        attackPoints += 2*playerLevel;
        output = output + "+" + 6*playerLevel +" Attack, ";
        defensePoints += playerLevel;
        output = output + "+" + 2*playerLevel + " Defense, ";
        arrowsCount += playerLevel*10;
        output = output + "+" + 10*playerLevel + " Arrows";
        return output;
    }

    @Override
    public Turn OnAbilityCast(Level level) {
        if(arrowsCount == 0)
            return new Turn("You don't have arrows.");
        List<Enemy> enemiesInRange = FindEnemies ( range, level.GetEnemies () );
        if(enemiesInRange.size () == 0)
            return new Turn("No enemy in range.");
        arrowsCount -= 1;
        String output = name + " fired an arrow at ";
        double minRang = Range (enemiesInRange.get ( 0 ));
        Enemy closest = enemiesInRange.get ( 0 );
        for(int i = 1; i < enemiesInRange.size (); i ++){
            if(minRang > Range ( enemiesInRange.get ( i ) )){
                minRang = Range ( enemiesInRange.get ( i ) );
                closest = enemiesInRange.get ( i );
            }
        }
        output += closest.GetName () + ".";
        int defense = closest.Defense ();
        output += "\n" + closest.GetName () + " rolled " + defense + " defense points.";
        int damage = Damage ( defense, attackPoints );
        output += "\n" +  name + " hit " + closest.GetName () + " for " + damage + " ability damage.";
        closest.AfterAttack ( damage );
        if (!closest.isAlive ( )) {
            output = output + "\n" + closest.GetName ( ) + " died. " + name + " gained " + closest.GetExperience ( ) + " experience.";
            output = output + UpdateExperience ( closest.GetExperience ( ) );
            level.GetBoard ( ).GetBoard ( )[closest.GetPosition ( ).x][closest.GetPosition ( ).y] = new EmptyTile ( closest.GetPosition ( ).x, closest.GetPosition ( ).y );
            closest.SetPosition ( null );
        }
        return new Turn(output);
    }

    @Override
    public void OnGameTick() {
        if(ticksCount == 10) {
            arrowsCount += playerLevel;
            ticksCount = 0;
        }
        else
            ticksCount ++;
    }
}
