package Model.Unit.Player;

import Model.Helpers.Health;
import Model.Helpers.Mana;

public enum Mages {

    Melisandre("Melisandre",new Health(100),5,1, new Mana ( 300, 30 ), 15, 5, 6 ),
    ThorosOfMyr("Thoros Of Myr", new Health ( 250 ), 25, 4, new Mana ( 150, 20 ), 20,3,4);

    String name;
    char tile = '@';
    Health health;
    int attackPoints;
    int defensePoints;
    Mana mana;
    int spellPower;
    int hitCount;
    int range;

    Mages(String name, Health health, int attackPoints, int defensePoints, Mana mana, int spellPower, int hitCount, int range){
        this.name = name;
        this.health = health;
        this.attackPoints = attackPoints;
        this. defensePoints = defensePoints;
        this.mana = mana;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.range = range;
    }

}
