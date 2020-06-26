package Model.Tile;

import Model.Unit.Visitor;

public interface Visited {

    public String accept(Visitor visitor);
}
