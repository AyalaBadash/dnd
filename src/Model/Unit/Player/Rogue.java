package Model.Unit.Player;

import View.Level;
import Model.Helpers.Energy;
import Model.Tile.EmptyTile;
import Model.Unit.Enemy.Enemy;
import View.Turn;

import java.util.List;

public class Rogue extends Player{

    int cost;
    Energy energy;

    public Rogue(int x, int y, Rogues r) {
        super ( r.tile, x, y );
        name = r.name;
        health = r.health;
        attackPoints = r.attackPoints;
        defensePoints = r.defensePoints;
        cost = r.cost;
        energy = new Energy ();
    }

    @Override
    public void OnGameTick() {
        energy.OnGameTick ();
    }

    @Override
    public String OnLevelUp(){
        String output = super.OnLevelUp ();
        energy.LevelUp ();
        attackPoints += 3*playerLevel;
        output = output + "+" + 7*playerLevel +" Attack, ";
        output = output + "+" + playerLevel + " Defense";
        return output;
    }

    @Override
    public Turn OnAbilityCast(Level level) {
        if(energy.GetCurrEnergy () < cost)
            return new Turn ( "You don't have enough energy yet." );
        String output = name + " cast Fan of Knives.";
        energy.AfterUsing (cost);
        List<Enemy> enemiesInRange = FindEnemies ( 2, level.GetEnemies () );
        if(enemiesInRange.size () == 0)
            return new Turn(output + "\nNo enemies in range.");
        for ( Enemy enemy: enemiesInRange ) {
            int defense = enemy.Defense ( );
            output = output + "\n" + enemy.GetName ( ) + " rolled " + defense + " defense points.";
            int damage = Damage ( defense, attackPoints );
            output = output + "\n" + name + " hit " + enemy.GetName ( ) + " for " + damage + " ability damage.";
            enemy.AfterAttack ( damage );
            if (!enemy.isAlive ( )) {
                output = output + "\n" + enemy.GetName ( ) + " died. " + name + " gained " + enemy.GetExperience ( ) + " experience.";
                output = output + UpdateExperience ( enemy.GetExperience ( ) );
                level.GetBoard ( ).GetBoard ( )[enemy.GetPosition ( ).x][enemy.GetPosition ( ).y] = new EmptyTile ( enemy.GetPosition ( ).x, enemy.GetPosition ( ).y );
                enemy.SetPosition ( null );
            }
        }
        return new Turn ( output );
    }

    @Override
    public String Describe() {
        String output = super.Describe ();
        String energyString = "Energy: " + energy.GetCurrEnergy () + "/" + energy.GetMaxEnergy ();
        output += energyString;
        return output;
    }


}
