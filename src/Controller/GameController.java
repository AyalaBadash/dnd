package Controller;
import Model.Unit.Enemy.Enemy;
import Model.Unit.Listener;
import Model.Unit.Player.Player;
import View.Turn;

import java.util.ArrayList;
import java.util.List;

public class GameController implements observer {

     private List<Listener> listeners = new ArrayList<> ();
     private List<Level> levels = new ArrayList<> ();
     private Level currLevel;
     private Player player;
     private InputReciever inputReciever = new InputReciever ();

    @java.lang.Override
    public void AddListener(Listener toAdd) {
        listeners.add ( toAdd );
    }

    @java.lang.Override
    public void Notify() {
        for ( Listener toNotify: listeners )
        {
            toNotify.OnGameTick ();
        }
    }

    public void InitializeGame(String path){
        char playerChoice = inputReciever.RecieveUserInput ();
        BoardCreator bc = new BoardCreator (playerChoice, path);
        levels = bc.ReadFromFolder ();
        player = bc.GetPlayer ();
        currLevel = levels.remove ( 0 );
        List<Enemy> enemies = currLevel.GetEnemies ();
        AddListener ( player );
        for ( Enemy e: enemies ) {
            AddListener ( e );
        }
    }

    public boolean HasNextLevel(){
        return !levels.isEmpty ();
    }

    public Level NextLevel(){
        return levels.remove ( 0 );
    }

    public void Play(){
        while ((currLevel != null) && player.isAlive ()){
            LevelGame ();
            if(HasNextLevel ())
                currLevel = NextLevel ();
            else
                currLevel = null;
        }
        if(!player.isAlive ())
            System.out.println ("Game Over");
        else
            System.out.println ("You Win");
    }

    public void LevelGame(){
        while (!currLevel.GetEnemies ().isEmpty() && player.isAlive ())
        {
            Round ();
        }
        if(player.isAlive ())
            System.out.println ("Level Ends");
    }

    public void Round(){
        PlayerMove ();
        for ( Enemy play : currLevel.GetEnemies () ) {
            EnemiesMove ( play );
        }
        currLevel.GetBoard ().Print ();
        Notify ();
    }

    public void PlayerMove(){
        char move = inputReciever.RecieveUserInput ();
        Turn turn;
        if(move =='e')
            turn = player.OnAbilityCast ( currLevel.GetEnemies () );
        else if(move != 'q')
            turn = player.OnPlayerTurn(move, currLevel.GetBoard ().GetBoard ());
        else
            turn = new Turn ( "" );
        turn.Print ();
    }

    public void EnemiesMove(Enemy play){
        Turn turn = play.OnEnemyTurn(currLevel.GetBoard ());
        turn.Print ();
    }

    public Level getCurrLevel() {
        return currLevel;
    }
}
