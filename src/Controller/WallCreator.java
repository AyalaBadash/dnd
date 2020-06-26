package Controller;

import Model.Tile.Tile;
import Model.Tile.Wall;

public class WallCreator implements Creator {
    @Override
    public Tile Create(char toCreate, int x, int y) {
        return new Wall ( x, y );
    }
}
