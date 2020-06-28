package Model.Unit.Enemy;

import Model.Helpers.Health;
import Model.Unit.HeroicUnit;
import View.Level;
import View.Turn;

import java.util.Random;

public class Boss extends Monster implements HeroicUnit {

    int abilityFrequency;
    int combatTicks;

    public Boss(int x, int y, Bosses b) {
        super ( b.tile, x,y );
        name = b.name;
        health = new Health ( b.health.GetHealthPool () );
        attackPoints = b.attack;
        defensePoints = b.defense;
        visionRange = b.visionRange;
        experienceValue = b.experienceValue;
        abilityFrequency = b.abilityFrequency;
        combatTicks = 0;
    }

    @Override
    public String Describe() {
        String output = super.Describe ();
        String abilityString = "Ability Frequency: " + abilityFrequency;
        output += abilityString;
        return output;
    }

    @Override
    public void OnGameTick() {
    }

    @Override
    public Turn OnEnemyTurn(Level level) {
        if(Range ( level.getCurrPlayer () ) < visionRange) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                return OnAbilityCast ( level );
            }
            combatTicks += 1;
            return super.OnEnemyTurn ( level );
        }
        combatTicks = 0;
        return new Turn ( RandomMove ( level.GetBoard () ) );
    }

    @Override
    public Turn OnAbilityCast(Level level) {
        String output = name + " shot at " + level.getCurrPlayer ().GetName () + ".";
        int defense = level.getCurrPlayer().Defense ();
        output = output + "\n" + level.getCurrPlayer().GetName () + " rolled " + defense + " defense points.";
        int damage = Damage ( defense, attackPoints);
        output = output + "\n" + name + " hit " + level.getCurrPlayer().GetName () + " for " + damage + " ability damage.";
        level.getCurrPlayer ().AfterAttack ( damage );
        return new Turn ( output );
    }
}
