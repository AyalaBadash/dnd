package Model.Unit.Player;

import Model.Helpers.Health;
import Model.Helpers.Mana;

public enum Hunters {

    Ygritte("Ygritte",new Health (220),30,2, 6 );

    String name;
    char tile = '@';
    Health health;
    int attackPoints;
    int defensePoints;
    int range;

    Hunters(String name, Health health, int attackPoints, int defensePoints, int range){
        this.name = name;
        this.health = health;
        this.attackPoints = attackPoints;
        this. defensePoints = defensePoints;
        this.range = range;
    }

}
