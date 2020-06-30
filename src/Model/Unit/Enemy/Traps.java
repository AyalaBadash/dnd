package Model.Unit.Enemy;

import Model.Helpers.Health;

public enum Traps {

    BonusTrap("Bonus Trap",'B',1,1,1,250,1,5),
    QueensTrap("Queens Trap",'Q',250,50,10,100,3,7),
    DeathTrap("Death Trap",'D',500,100,20,250,1,10);

    String name;
    char tile;
    Health health;
    int attack;
    int defense;
    int experienceValue;
    int visibilityTime;
    int invisibilityTime;

    Traps(String name, char tile, int health, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime){
        this.name = name;
        this.tile = tile;
        this.health = new Health (health);
        this.attack = attack;
        this.defense = defense;
        this.experienceValue = experienceValue;
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
    }

}
