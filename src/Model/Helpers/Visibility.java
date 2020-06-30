package Model.Helpers;

public class Visibility {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Visibility (int visibilityTime, int invisibilityTime){
        this.invisibilityTime = invisibilityTime;
        this.visibilityTime = visibilityTime;
        visible = true;
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
        if(ticksCount == (visibilityTime + invisibilityTime - 1))
            ticksCount = 0;
        else
            ticksCount = ticksCount + 1;
    }
}
