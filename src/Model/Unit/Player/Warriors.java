package Model.Unit.Player;

import Model.Helpers.Cooldown;
import Model.Helpers.Health;

public enum Warriors {

    JonSnow("Jon Snow", new Health (300), 30, 4, new Cooldown ( 3 ) ),
    TheHound("The Hound", new Health ( 400 ), 20,6,new Cooldown ( 5 ));

    String name;
    char tile = '@';
    Health health;
    int attackPoints;
    int defensePoints;
    Cooldown cooldown;

    Warriors( String name, Health health, int attackPoints, int defensePoints, Cooldown cooldown){
        this.name = name;
        this.health = health;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.cooldown = cooldown;
    }



}
