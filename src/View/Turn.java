package View;

public class Turn implements Printer {

    String turnDescribe;

    public Turn(String turn)
    {
        turnDescribe = turn;
    }

    @Override
    public void Print() {
        if(turnDescribe.length () > 0)
            System.out.println (turnDescribe );
    }
}
