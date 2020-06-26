package Model.Helpers;

public class Energy {
    int maxEnergy = 100;
    int currEnergy;

    public Energy(){currEnergy = 100;}

    public void OnGameTick(){
        currEnergy = Math.min ( currEnergy + 10 , maxEnergy );
    }

    public int GetMaxEnergy(){return maxEnergy;}
    public int GetCurrEnergy(){return currEnergy;}
}
