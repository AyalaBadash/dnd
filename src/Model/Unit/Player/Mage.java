package Model.Unit.Player;

import Model.Helpers.Mana;
import Model.Tile.EmptyTile;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Visitor;
import View.Turn;

import java.util.List;

public class Mage extends Player {

    Mana mana;
    int spellPower;
    int hitCount;
    int range;

    public Mage(int x, int y, Mages m) {
        super ( m.tile, x, y );
        name = m.name;
        health = m.health;
        attackPoints = m.attackPoints;
        defensePoints = m.defensePoints;
        mana = m.mana;
        spellPower = m.spellPower;
        hitCount = m.hitCount;
        range = m.range;
    }

    @Override
    public void OnGameTick() {
        mana.OnGameTick(playerLevel);
    }

    @Override
    public Turn OnAbilityCast(List<Enemy> enemies) {
        return null;
    }

    @Override
    public String Describe() {
        String output = name;
        for(int i = name.length (); i <= 25; i++)
            output += " ";
        String healthString = "Health: " + health.GetHealthAmount ()+"/" + health.GetHealthPool ();
        output += healthString;
        for(int i = healthString.length (); i <= 25; i++)
            output += " ";
        String attackString = "Attack: "+ attackPoints;
        output += attackString;
        for(int i = attackString.length (); i <= 25; i++)
            output += " ";
        String defenseString = "Defense: "+ defensePoints;
        output += defenseString;
        for(int i = defenseString.length (); i <= 25; i++)
            output += " ";
        String levelString = "Level: "+ playerLevel;
        output += levelString;
        for(int i = levelString.length (); i <= 25; i++)
            output += " ";
        String experienceString = "Experience: "+ experience + "/" + "50";
        output += experienceString;
        for(int i = experienceString.length (); i <= 25; i++)
            output += " ";
        String manaString = "Mana: " + mana.GetCurrMana ()+"/"+mana.GetManaPool ();
        output += manaString;
        for(int i = manaString.length (); i <= 25; i++)
            output += " ";
        String spellPowerString = "SpellPower: "+ spellPower;
        output += spellPowerString;
        return output;
    }

    @Override
    public String Visit(Enemy visit) {
        return null;
    }

    @Override
    public String Visit(Player visit) {
        return null;
    }

}
