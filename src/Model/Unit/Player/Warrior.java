package Model.Unit.Player;

import View.Level;
import Model.Helpers.Cooldown;
import Model.Tile.EmptyTile;
import Model.Unit.Enemy.Enemy;
import View.Turn;

public class Warrior extends Player {

    Cooldown cooldown;

    public Warrior(int x, int y, Warriors w) {
        super ( w.tile, x, y );
        name = w.name;
        health = w.health;
        attackPoints = w.attackPoints;
        defensePoints = w.defensePoints;
        cooldown = w.cooldown;
    }

    @Override
    public String OnLevelUp(){
        String output = "";
        output = super.OnLevelUp ();
        cooldown.LevelUp ();
        health.SetHealthPool ( health.GetHealthPool () + 5*playerLevel );
        attackPoints += 2*playerLevel;
        output = output + "+" + 6*playerLevel +" Attack, ";
        defensePoints += playerLevel;
        output = output + "+" + 2*playerLevel + " Defense";
        return output;
    }

    @Override
    public Turn OnAbilityCast(Level level) {
        if (cooldown.GetRemainingCooldown ( ) > 0)
            return new Turn ( "You still need to cooldown..." );
        String output = name + " used Avenger's Shield, ";
        int healing = health.UpdateHealthAmount ( 10 * defensePoints );
        output = output + "healing for " + healing;
        cooldown.AfterUsing ( );
        Enemy enemy = RandomEnemyInRange ( 3, level.GetEnemies ( ) );
        if (enemy == null)
            return new Turn ( output + "\nNo enemy in range." );
        int defense = enemy.Defense ( );
        output = output + "\n" + enemy.GetName ( ) + " rolled " + defense + " defense points.";
        int damage = Damage ( defense, health.GetHealthPool ( ) / 10 );
        output = output + "\n" + name + " hit " + enemy.GetName ( ) + " for " + damage + " ability damage.";
        enemy.AfterAttack ( damage );
        if (!enemy.isAlive ( )) {
            output = output + "\n" + enemy.GetName ( ) + " died. " + name + " gained " + enemy.GetExperience ( ) + " experience.";
            output = output + UpdateExperience ( enemy.GetExperience ( ) );
            level.GetBoard ( ).GetBoard ( )[enemy.GetPosition ( ).x][enemy.GetPosition ( ).y] = new EmptyTile ( enemy.GetPosition ( ).x, enemy.GetPosition ( ).y );
            enemy.SetPosition ( null );
        }
        return new Turn(output);
    }

    @Override
    public void OnGameTick() {
        cooldown.OnGameTick ();
    }

    @Override
    public String Describe() {
        String output = super.Describe ();
        String cooldownString = "Cooldown: " + cooldown.GetRemainingCooldown () + "/" + cooldown.GetAbilityCooldown ();
        output += cooldownString;
        return output;
    }

}
