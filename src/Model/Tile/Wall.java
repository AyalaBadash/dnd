package Model.Tile;

import Model.Unit.Visitor;

import java.awt.*;

public class Wall extends Tile{

    public Wall(int x, int y) {
        super ( '#', x,y );
    }

    @Override
    public String accept(Visitor visitor) {
        return "";
    }
}
