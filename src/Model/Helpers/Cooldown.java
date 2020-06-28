package Model.Helpers;

public class Cooldown {
    private int abilityCooldown;
    private int remainingCooldown;
    private boolean usedLastTurn;

    public Cooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
        usedLastTurn = false;
    }

    public void SetRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public void SetAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public int GetAbilityCooldown() {
        return abilityCooldown;
    }

    public int GetRemainingCooldown() {
        return remainingCooldown;
    }


    public void OnGameTick() {
        if(usedLastTurn)
            usedLastTurn = false;
        else if (remainingCooldown > 0)
            remainingCooldown--;
    }

    public void AfterUsing() {
        remainingCooldown = abilityCooldown;
        usedLastTurn = true;
    }

    public void LevelUp() {
        remainingCooldown = 0;
    }
}
