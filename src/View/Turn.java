package View;

public class Turn implements Printer {

    String turnDescribe;

    public Turn(String turn)
    {
        turnDescribe = turn;
    }

    @Override
    public void Print() {
        System.out.println (turnDescribe );
    }

    public void SetTurnDescribe(String turnDescribe){this.turnDescribe = turnDescribe;}
}
