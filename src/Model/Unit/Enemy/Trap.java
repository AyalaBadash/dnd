package Model.Unit.Enemy;

import Model.Helpers.Visibility;
import Model.Tile.Tile;
import View.Level;
import View.Turn;

public class Trap extends Enemy {

    protected Visibility visibility;

    public Trap(int x, int y, Traps t) {
        super ( t.tile, x, y );
        name = t.name;
        health = t.health;
        attackPoints = t.attack;
        defensePoints = t.defense;
        experienceValue = t.experienceValue;
        visibility = new Visibility ( t.visibilityTime, t.invisibilityTime );
    }

    @Override
    public String Describe() {
        String output = super.Describe ();
        return output;
    }

    @Override
    public void OnGameTick() {
        visibility.UpdateVisibility();
    }


    @Override
    public Turn OnEnemyTurn(Level level) {
        Tile[][] currBoard = level.GetBoard ().GetBoard ();
        String output = "";
        if(Range( level.getCurrPlayer () )< 2)
        {
            output = level.getCurrPlayer ().accept ( this );
        }
        return new Turn ( output );
    }


    @Override
    public String toString(){
        if(visibility.GetVisible ())
            return ""+tile;
        else
            return ".";
    }

}
