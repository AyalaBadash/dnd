package Model.Tile;

import Model.Unit.Visitor;

import java.awt.*;

public abstract class Tile implements Visited {

    protected char tile;
    protected Point position;

    public Tile(char tile, int x, int y)
    {
        this.tile = tile;
        position = new Point ( x,y );
    }

    public Point GetPosition(){return position;}
    public void SetPosition(Point position){this.position = position;}

    public double Range(Tile other) {
        return position.distance ( other.position );
    }

    public String toString() {
        return "" + tile;
    }

    public abstract String accept(Visitor visitor);

}
