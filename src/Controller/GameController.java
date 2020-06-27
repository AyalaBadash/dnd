package Controller;

import Model.Unit.Enemy.Enemy;
import Model.Unit.Listener;
import Model.Unit.Player.Player;
import View.Level;
import View.MessageHandler;
import View.PlayersMenuChoice;
import View.Turn;

import java.util.ArrayList;
import java.util.List;

public class GameController implements observer {

     private List<Listener> listeners = new ArrayList<> ();
     private List<Level> levels = new ArrayList<> ();
     private Level currLevel;
     private Player player;
     private InputReciever inputReciever = new InputReciever ();
     private MessageHandler printer = new MessageHandler();

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
        PlayersMenuChoice menu = new PlayersMenuChoice ();
        menu.Print ();
        printer.Print ( "Choose a player." );
        char playerChoice = inputReciever.RecieveUserInput ();
        BoardCreator bc = new BoardCreator (playerChoice, path);
        levels = bc.ReadFromFolder ();
        player = bc.GetPlayer ();
        currLevel = levels.remove ( 0 );
        player.SetPosition ( currLevel.getPlayerStartPosition () );
        List<Enemy> enemies = currLevel.GetEnemies ();
        AddListener ( player );
        for ( Enemy e: enemies ) {
            AddListener ( e );
        }
    }

    public void Play(){
        while ((currLevel != null) && player.isAlive ()){
            LevelGame ();
            if(HasNextLevel ())
                currLevel = NextLevel ();
            else
                currLevel = null;
        }
        if(player.isAlive ())
            printer.Print ("You Win");
    }

    public void LevelGame(){
        while (!currLevel.GetEnemies ().isEmpty() && player.isAlive ())
        {
            Round ();
        }
        if(player.isAlive ())
            printer.Print ("Level Ends");
    }

    public void Round(){
        currLevel.Print ();
        PlayerMove ();
        for ( Enemy play : currLevel.GetEnemies () ) {
            EnemiesMove ( play );
        }
        Notify ();
    }

    public void PlayerMove(){
        char move = inputReciever.RecieveUserInput ();
        Turn turn = player.OnPlayerTurn (move, currLevel );
        turn.Print ();
    }

    public void EnemiesMove(Enemy play){
        Turn turn = play.OnEnemyTurn(currLevel.GetBoard ());
        turn.Print ();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public boolean HasNextLevel(){
        return !levels.isEmpty ();
    }

    public Level NextLevel(){
        Level next = levels.remove ( 0 );
        player.SetPosition ( next.getPlayerStartPosition () );
        return next;
    }

    public Level getCurrLevel() {
        return currLevel;
    }
}
