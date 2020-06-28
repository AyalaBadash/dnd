package Model.Unit.Enemy;

import Model.Unit.Player.Player;
import Model.Unit.Unit;
import Model.Unit.Visitor;
import View.GameBoard;
import View.Level;
import View.Turn;

import java.util.Random;

public abstract class Enemy extends Unit {

    protected int experienceValue;

    public Enemy(char tile, int x, int y) {
        super ( tile, x, y );
    }

    public int GetExperience(){return experienceValue;}

    @Override
    public String accept(Visitor visitor) {
        return visitor.Visit ( this );
    }

    @Override
    public String Visit(Enemy visited){
        return  "";
    }

    @Override
    public String Visit(Player visited){
        String output = name + " engaged in combat with " + visited.GetName () + ".";
        output = output + "\n" + Describe ();
        output = output + "\n" + visited.Describe ();
        int attackRolled = Attack ();
        output = output + "\n" + name + " rolled " + attackRolled + " attack points.";
        int defenseRolled = visited.Defense ();
        output = output + "\n" + visited.GetName () + " rolled " + defenseRolled + " defense points.";
        int damage = Damage ( defenseRolled, attackRolled );
        output = output + "\n" + name + " dealt " + damage + " demage to " + visited.GetName ()+ ".";
        visited.AfterAttack(damage);
        if(!visited.isAlive ())
        {
            output = output + "\n" + visited.GetName () + " was killed by " + name + ".";
            output = output + "\nYou lost.";
        }
        return output;
    }

    public String Describe(){
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
        String experienceString = "Experience: "+ experienceValue;
        output += experienceString;
        for(int i = experienceString.length (); i <= 25; i++)
            output += " ";
        return output;
    }

    public void Dead(){
        isAlive = false;
    }

    @Override
    public abstract void OnGameTick();

    public abstract Turn OnEnemyTurn(Level level);
}
