package Model.Enemy;

public enum Minions {

    LannisterSoldier('s',80,8,3,3,25),
    LannisterKnight('k', 200, 14, 8, 4, 50),
    QueensGuard('q', 400, 20, 15,5,100),
    Wright('z', 600, 30, 15, 3,100),
    BearWright('b', 1000,75,30,4,250),
    GiantWright('g', 1500,100,40,5,500),
    WhiteWalker('w',2000,150,50,6,1000),
    TheMountain('M',1000,60,25,6,500),
    QueenCersei('C',100,10,10,1,1000),
    NightsKing('K',5000,300,150,8,5000);

    char tile;
    int health;
    int attack;
    int defense;
    int visionRange;
    int experienceValue;

    Minions(char tile, int health, int attack, int defence, int visionRange, int experienceValue)
    {
        this.tile = tile;
        this.health = health;
        this.attack = attack;
        this.defense = defence;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
    }
}
