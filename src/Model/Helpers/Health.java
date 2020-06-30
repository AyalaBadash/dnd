package Model.Helpers;

public class Health {
    private int healthPool;
    private int healthAmount;

    public Health(int healthPool){
        this.healthPool = healthPool;
        healthAmount = healthPool;
    }

    public void SetHealthPool(int healthPool){this.healthPool = healthPool;}
    public void SetHealthAmount(int healthAmount){
        if (healthAmount <= 0)
            this.healthAmount = 0;
        else
            this.healthAmount = Math.min (healthAmount, healthPool);
    }
    public int GetHealthPool(){return healthPool;}
    public int GetHealthAmount(){return healthAmount;}

    public int UpdateHealthAmount(int add){
        if (healthAmount + add > healthPool) {
            int output = healthPool - healthAmount;
            healthAmount = healthPool;
            return output;
        }
        else
            healthAmount = healthAmount + add;
        return add;
    }
}
