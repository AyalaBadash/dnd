package Model.Unit.Player;

import Model.Helpers.Health;

public enum Rogues {

    AryaStark("Arya Stark", new Health ( 150 ), 40,2,20),
    Bronn("Bronn", new Health ( 250 ), 35, 3, 50);

    String name;
    char tile ='@';
    Health health;
    int attackPoints;
    int defensePoints;
    int cost;

    Rogues(String name, Health health, int attackPoints, int defensePoints, int cost){
        this.name = name;
        this.health = health;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.cost = cost;
    }
}
