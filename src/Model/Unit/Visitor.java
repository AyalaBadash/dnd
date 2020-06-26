package Model.Unit;

import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Wall;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;

public interface Visitor {

    String Visit(EmptyTile visit);

    String Visit(Enemy visit);

    String Visit(Player visit);
}
