package Model.Unit.Player;

import Model.Helpers.Energy;
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
    public Turn OnAbilityCast(List<Enemy> enemies) {
        return null;
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
        String energyString = "Energy: " + energy.GetCurrEnergy () + "/" + energy.GetMaxEnergy ();
        output += energyString;
        return output;
    }


}
