package Model.Helpers;

public class Visibility {
    int visibilityTime;
    int invisibilityTime;
    int ticksCount;
    boolean visible;

    public Visibility (int visibilityTime, int invisibilityTime){
        this.invisibilityTime = invisibilityTime;
        this.visibilityTime = visibilityTime;
        ticksCount = 0;
    }

    public boolean GetVisible(){ return visible;}
    public int GetVisibilityTime(){return visibilityTime;}
    public  int GetInvisibilityTime(){return invisibilityTime;}
    public int GetTickCount(){return ticksCount;}
    public void SetVisibilityTime(int visibilityTime){this.visibilityTime = visibilityTime;}
    public void SeInvisibilityTime(int invisibilityTime){this.invisibilityTime = invisibilityTime;}
    public void SetTickCount(int tickCount){this.ticksCount = tickCount;}

    public void UpdateVisibility() {
        visible = ticksCount < visibilityTime;
        if(ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount = ticksCount + 1;
    }
}
