package Model.Helpers;

public class Health {
    int healthPool;
    int healthAmount;

    public Health(int healthPool){
        this.healthPool = healthPool;
        healthAmount = healthPool;
    }

    public void SetHealthPool(int healthPool){this.healthPool = healthPool;}
    public void SetHealthAmount(int healthAmount){this.healthAmount = Math.min (healthAmount, healthPool);}
    public int GetHealthPool(){return healthPool;}
    public int GetHealthAmount(){return healthAmount;}
}
