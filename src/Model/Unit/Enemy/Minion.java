package Model.Unit.Enemy;

import Model.Helpers.Health;

public class Minion extends Monster {

    public Minion (int x, int y, Minions m){
        super(m.tile, x, y);
        name = m.name;
        health = new Health ( m.health.GetHealthPool () );
        attackPoints = m.attack;
        defensePoints = m.defense;
        visionRange = m.visionRange;
        experienceValue = m.experienceValue;
    }
}
