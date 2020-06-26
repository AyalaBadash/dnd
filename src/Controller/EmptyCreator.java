package Controller;

import Model.Tile.EmptyTile;
import Model.Tile.Tile;

import java.awt.*;

public class EmptyCreator implements Creator {
    @Override
    public Tile Create(char toCreate, int x, int y) {
        return new EmptyTile ( x,y );
    }
}
