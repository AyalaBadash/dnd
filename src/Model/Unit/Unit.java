package Model.Unit;

import Controller.Level;
import Model.Helpers.Health;
import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Visited;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;

import java.awt.*;
import java.util.Random;

public abstract class Unit extends Tile implements Visitor, Visited, Listener {

    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected Tile[][] currBoard;
    protected boolean isAlive = true;

    public Unit(char tile, int x, int y)
    {
        super ( tile, x, y );
    }

    public String GetName(){return name;}

    public boolean isAlive() {
        return isAlive;
    }

    public abstract String Describe();

    public abstract void OnGameTick();

    public abstract String accept(Visitor visitor);

    public abstract String Visit(Enemy visited);

    public String Visit(EmptyTile visit){
        Switch ( visit, currBoard );
        return "";
    }

    public String Died(){
        isAlive = false;
        return name + " died.";
    }

    public abstract String Visit(Player visit);

    public void Switch(Tile toSwitch, Tile[][] board){
        board[position.x][position.y] = board[toSwitch.GetPosition ().x][toSwitch.GetPosition ().y];
        board[toSwitch.GetPosition ().x][toSwitch.GetPosition ().y] = board[position.x][position.y];
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

}
