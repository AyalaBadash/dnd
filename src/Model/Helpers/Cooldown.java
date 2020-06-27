package Model.Helpers;

public class Cooldown {
    private int abilityCooldown;
    private int remainingCooldown;

    public Cooldown(int abilityCooldown) {
        if (abilityCooldown < 0)
            throw new IllegalArgumentException("Ability Cooldown is negative");
        this.abilityCooldown = abilityCooldown;
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


    public void AfterTick() {
        if (remainingCooldown > 0) remainingCooldown--;
    }

    public void AfterUsing() {
        remainingCooldown = abilityCooldown;
    }

    public void LevelUp() {
        remainingCooldown = 0;
    }
}
