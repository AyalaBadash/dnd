package Model.Helpers;

public class Mana {
    int manaPool;
    int manaCost;
    int currMana;

    public Mana(int manaPool, int manaCost) {
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        currMana = manaPool / 4;
    }

    public int GetManaPool() {
        return manaPool;
    }

    public int GetManaCost() {
        return manaCost;
    }

    public int GetCurrMana() {
        return currMana;
    }

    public void SetManaPool(int manaPool) {
        this.manaPool = manaPool;
    }

    public void SetManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void SetCurrMana(int currMana) {
        this.currMana = Math.min (currMana, manaPool) ;

    }

    public void OnGameTick(int level) {
        SetCurrMana ( currMana + level );
    }
}