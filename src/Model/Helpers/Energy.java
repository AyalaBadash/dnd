package Model.Helpers;

public class Energy {
    private int maxEnergy = 100;
    private int currEnergy;
    private boolean isUsedLastTurn;

    public Energy(){
        currEnergy = 100;
        isUsedLastTurn = false;
    }

    public void OnGameTick(){
        if(isUsedLastTurn)
            isUsedLastTurn = false;
        else
            currEnergy = Math.min ( currEnergy + 10 , maxEnergy );
    }
    public void LevelUp(){currEnergy = maxEnergy;}
    public int GetMaxEnergy(){return maxEnergy;}
    public int GetCurrEnergy(){return currEnergy;}

    public void AfterUsing(int currEnergy) {
        isUsedLastTurn = true;
        this.currEnergy -= currEnergy;
    }
}
