package Model.Unit.Enemy;

import Model.Helpers.Health;

public enum Minions {

    LannisterSoldier("LannisterSoldier",'s',80,8,3,3,25),
    LannisterKnight("LannisterKnight",'k', 200, 14, 8, 4, 50),
    QueensGuard("QueensGuard",'q', 400, 20, 15,5,100),
    Wright("Wright",'z', 600, 30, 15, 3,100),
    BearWright("BearWright",'b', 1000,75,30,4,250),
    GiantWright("GiantWright",'g', 1500,100,40,5,500),
    WhiteWalker("WhiteWalker",'w',2000,150,50,6,1000),
    TheMountain("TheMountain",'M',1000,60,25,6,500),
    QueenCersei("QueenCersei", 'C',100,10,10,1,1000),
    NightsKing("NightsKing",'K',5000,300,150,8,5000);

    protected String name;
    protected char tile;
    protected Health health;
    protected int attack;
    protected int defense;
    protected int visionRange;
    protected int experienceValue;

    Minions(String name, char tile, int health, int attack, int defence, int visionRange, int experienceValue)
    {
        this.name = name;
        this.tile = tile;
        this.health = new Health ( health );
        this.attack = attack;
        this.defense = defence;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
    }
}
