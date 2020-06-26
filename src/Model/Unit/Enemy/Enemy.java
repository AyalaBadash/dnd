package Model.Unit.Enemy;

import Model.Unit.Player.Player;
import Model.Unit.Unit;
import Model.Unit.Visitor;
import View.GameBoard;
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

    public String Defense(int attack) {
        Random random = new Random ( );
        int defense = random.nextInt ( defensePoints );
        int demageTeaken = attack - defense;
        demageTeaken = (demageTeaken <= 0) ? 0 : demageTeaken;
        health.SetHealthAmount ( health.GetHealthAmount () - demageTeaken );
        return ""+ name +" rolled " + defense + " points. \n";
    }

    @Override
    public abstract void OnGameTick();

    public abstract Turn OnEnemyTurn(GameBoard board);

    @Override
    public String Visit(Enemy visited){
        return  "";
    }

    @Override
    public String Visit(Player visit){
        String output = name + " engaged in combat with " + visit.GetName () + ".";
        output = output + "\n" + Describe ();
        output = output + "\n" + visit.Describe ();
        int attackRolled = Attack ();
        output = output + "\n" + name + " rolled " + attackRolled + " attack points.";
        int defenseRolled = visit.Defense ();
        output = output + "\n" + visit.GetName () + " rolled " + defenseRolled + " defense points.";
        output = output + "\n" + name + " dealt " + Damage (defenseRolled, attackRolled ) + " demage to " + visit.GetName ()+ ".";
        output = !visit.isAlive () ? output + "\n Game Over" : output + "";
        return output;
    }
}
