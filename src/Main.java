import Controller.GameController;
import View.Level;

public class Main {
    public static void main(String[] args) {
        GameController gc = new GameController ();
        gc.InitializeGame ( args[0] );
        gc.Play();
    }
}
