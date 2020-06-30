package Model.Unit.Enemy;

import Model.Helpers.Health;

public enum Bosses {
    TheMountain("The Mountain",'M',1000,60,25,6,500, 7),
    QueenCersei("Queen Cersei", 'C',100,10,10,1,1000, 5),
    NightsKing("Nights King",'K',5000,300,150,8,5000, 10);

    protected String name;
    protected char tile;
    protected Health health;
    protected int attack;
    protected int defense;
    protected int visionRange;
    protected int experienceValue;
    protected int abilityFrequency;

    Bosses(String name, char tile, int health, int attack, int defence, int visionRange, int experienceValue, int abilityFrequency)
    {
        this.name = name;
        this.tile = tile;
        this.health = new Health ( health );
        this.attack = attack;
        this.defense = defence;
        this.visionRange = visionRange;
        this.experienceValue = experienceValue;
        this.abilityFrequency = abilityFrequency;
    }
}
