package Model.Tile;

import Model.Unit.Visitor;

import java.awt.*;
import java.io.PipedOutputStream;

public class EmptyTile extends Tile {

    public EmptyTile(int x, int y)
    {
        super('.',  x, y );
    }

    @Override
    public String  accept(Visitor visitor) {
        return visitor.Visit ( this );
    }
}
