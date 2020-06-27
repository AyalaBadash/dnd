package Model.Unit;

import Model.Tile.EmptyTile;
import Model.Tile.Tile;
import Model.Tile.Wall;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Player.Player;
import Model.Unit.Player.Warrior;

public interface Visitor {

    String Visit(EmptyTile visited);

    String Visit(Enemy visited);

    String Visit(Player visited);

    String Visit(Wall visited);
}
