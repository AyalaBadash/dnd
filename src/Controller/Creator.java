package Controller;

import Model.Tile.Tile;

public interface Creator {
    Tile Create(char toCreate, int x, int y);
}
