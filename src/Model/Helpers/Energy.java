package Model.Helpers;

public class Energy {
    private int maxEnergy = 100;
    private int currEnergy;

    public Energy(){currEnergy = 100;}

    public void OnGameTick(){
        currEnergy = Math.min ( currEnergy + 10 , maxEnergy );
    }
    public void OnLevelUp(){currEnergy = maxEnergy;}
    public int GetMaxEnergy(){return maxEnergy;}
    public int GetCurrEnergy(){return currEnergy;}
}
