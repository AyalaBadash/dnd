package Model.Helpers;

public class Mana {
    private int manaPool;
    private int manaCost;
    private int currMana;
    private boolean isUsedLastTurn;

    public Mana(int manaPool, int manaCost) {
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        currMana = manaPool / 4;
        isUsedLastTurn = false;
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
        this.currMana = Math.min ( currMana, manaPool );
    }

    public void AfterUsing() {
        isUsedLastTurn = true;
        currMana = currMana - manaCost;
    }

    public void OnGameTick(int level) {
        if (isUsedLastTurn)
            isUsedLastTurn = false;
        else
            SetCurrMana ( currMana + level );
    }
}
