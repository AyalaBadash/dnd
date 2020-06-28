package Model.Unit.Player;

import View.Level;
import Model.Helpers.Mana;
import Model.Tile.EmptyTile;
import Model.Unit.Enemy.Enemy;
import View.Turn;

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
    public String OnLevelUp(){
        String output = "";
        output = super.OnLevelUp ();
        output = output + "+" + 4*playerLevel +" Attack, ";
        output = output + "+" + playerLevel + " Defense";
        mana.SetManaPool ( mana.GetManaPool () + 25* playerLevel );
        mana.SetCurrMana ( mana.GetManaPool ()/4 );
        output = output + "\n                         +" + 25*playerLevel +" naximum mana, ";
        spellPower += 10*playerLevel;
        output = output + "+" + 10*playerLevel +" spell power";
        return output;
    }

    @Override
    public Turn OnAbilityCast(Level level) {
        String output = "";
        if(mana.GetCurrMana () < mana.GetManaCost ())
            return new Turn ( "Can't use special ability when your current mana is lower then mana cost." );
        output = output + name + " cast Blizzard.";
        mana.AfterUsing ();
        int hits = 0;
        Enemy enemy = RandomEnemyInRange ( range, level.GetEnemies ());
        if(enemy == null)
        {
            output = output + "\n No enemies in range.";
            return new Turn(output);
        }
        while (hits < hitCount & enemy != null ){
            int defense = enemy.Defense ();
            output = output + "\n" + enemy.GetName () + " rolled " + defense + " defense points.";
            int damage = Damage ( defense,  spellPower);
            output = output + "\n" + name + " hit " + enemy.GetName () + " for " + damage + " ability damage.";
            enemy.AfterAttack ( damage );
            if(!enemy.isAlive ()){
                output = output + "\n" + enemy.GetName () + " died. " + name + " gained " + enemy.GetExperience () + " experience.";
                output = output + UpdateExperience(enemy.GetExperience ());
                level.GetBoard ().GetBoard ()[enemy.GetPosition ().x][enemy.GetPosition ().y] = new EmptyTile ( enemy.GetPosition ().x, enemy.GetPosition ().y);
                enemy.SetPosition ( null );
                level.GetEnemies ().remove ( enemy );
                enemy = RandomEnemyInRange ( range, level.GetEnemies () );
            }
        }
        return new Turn ( output);
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
        String experienceString = "Experience: "+ experience + "/" + playerLevel*50;
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

}
