package Model.Unit;

import Model.Helpers.Health;
import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Visited;
import Model.Tile.Wall;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;

import java.awt.*;
import java.util.Random;

public abstract class Unit extends Tile implements Visitor, Visited, Listener {

    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected boolean isAlive = true;

    public Unit(char tile, int x, int y)
    {
        super ( tile, x, y );
    }

    public String GetName(){return name;}

    public boolean isAlive() {
        return isAlive;
    }

    public String Visit(EmptyTile visited){
        Switch ( visited );
        return "";
    }

    public String Visit(Wall visited){
        return "";
    }

    public void Switch(Tile toSwitch){
        Point temp = toSwitch.GetPosition ();
        toSwitch.SetPosition (new Point ( this.position ));
        this.position = temp;
    }

    public int Attack(){
        Random random = new Random (  );
        return random.nextInt ( attackPoints );
    }

    public int Defense(){
        Random random = new Random (  );
        return random.nextInt ( defensePoints );
    }

    public int Damage(int defensePoints, int attackPoints){
        return Math.max (attackPoints - defensePoints, 0);
    }

    public void AfterAttack(int damage){
        health.SetHealthAmount ( health.GetHealthAmount () - damage );
        if(health.GetHealthAmount () <= 0)
            Dead();
    }

    protected abstract void Dead();

    public abstract String Describe();

    public abstract void OnGameTick();

    public abstract String accept(Visitor visitor);

    public abstract String Visit(Enemy visited);

    public abstract String Visit(Player visit);

}
