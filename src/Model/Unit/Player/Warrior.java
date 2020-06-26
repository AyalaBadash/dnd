package Model.Unit.Player;

import Model.Helpers.Cooldown;
import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Visited;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Visitor;
import View.Turn;

import java.util.List;
import java.util.Random;

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
    public void OnLevelUp(){
        super.OnLevelUp ();
        cooldown.LevelUp ();
        health.SetHealthPool ( health.GetHealthPool () + 5*playerLevel );
        attackPoints += 2*playerLevel;
        defensePoints += playerLevel;
    }

    @Override
    public Turn OnAbilityCast(List<Enemy> enemiesInLevel) {
        if(cooldown.GetRemainingCooldown () > 0)
            throw new IllegalStateException ( "You still need to cooldown...");
        cooldown.AfterUsing ();
        List<Enemy> enemies = super.FindEnemies(3,enemiesInLevel );
        Random random = new Random ();
        int randomEnemy = random.nextInt ( enemies.size () - 1);
        Enemy enemy = enemies.get ( randomEnemy );
        return null;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.Visit ( this );
    }

    @Override
    public void OnGameTick() {
        cooldown.AfterTick ();
    }

    @Override
    public String Describe() {
        String output = name;
        for(int i = name.length (); i <= 25; i++)
            output += " ";
        String healthString = "Health: " + health.GetHealthAmount ()+"/" + health.GetHealthPool ();
        output += healthString;
        for(int i = healthString.length (); i <= 25; i++)
            output += " ";
        String attackString = "Attack: "+ attackPoints;
        output += attackString;
        for(int i = attackString.length (); i <= 25; i++)
            output += " ";
        String defenseString = "Defense: "+ defensePoints;
        output += defenseString;
        for(int i = defenseString.length (); i <= 25; i++)
            output += " ";
        String levelString = "Level: "+ playerLevel;
        output += levelString;
        for(int i = levelString.length (); i <= 25; i++)
            output += " ";
        String experienceString = "Experience: "+ experience + "/" + "50";
        output += experienceString;
        for(int i = experienceString.length (); i <= 25; i++)
            output += " ";
        String cooldownString = "Cooldown: " + cooldown.GetRemainingCooldown () + "/" + cooldown.GetAbilityCooldown ();
        output += cooldownString;
        return output;
    }

    @Override
    public String Visit(EmptyTile visit) {
        return null;
    }

    @Override
    public String Visit(Enemy visit) {
        return null;
    }

    @Override
    public String Visit(Player visit) {
        return null;
    }

}
