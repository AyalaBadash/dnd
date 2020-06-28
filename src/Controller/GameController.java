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

    //initialize the game - create the levels and get the user choice of a player
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

    //run the game
    public void Play(){
        while ((currLevel != null) && player.isAlive ()){
            LevelGame ();
            if(HasNextLevel ())
                currLevel = NextLevel ();
            else
                currLevel = null;
        }
        if(player.isAlive ())
            printer.Print ("You Won!");
    }

    //run each level at a time
    public void LevelGame(){
        while (currLevel.isStillOn ())
        {
            Round ();
        }
    }

    //run each round at a time - player's move and than each enemy's move
    public void Round(){
        currLevel.Print ();
        PlayerMove ();
        Enemy isDead= null;
        for ( Enemy play : currLevel.GetEnemies () ) {
            if(!play.isAlive ())
                isDead = play;
            else {
                EnemiesMove ( play );
                if (!player.isAlive ( )) {
                    currLevel.Print ( );
                    printer.Print ( "Game Over." );
                    break;
                }
            }
        }
        if (isDead != null)
            currLevel.GetEnemies ().remove ( isDead );
        Notify ();
    }


    public void PlayerMove(){
        char move = inputReciever.RecieveUserInput ();
        Turn turn = player.OnPlayerTurn (move, currLevel );
        turn.Print ();
    }

    public void EnemiesMove(Enemy play){
        Turn turn = play.OnEnemyTurn(currLevel);
        turn.Print ();
    }

    public boolean HasNextLevel(){
        return !levels.isEmpty ();
    }

    //update the next level and the start player's position
    public Level NextLevel(){
        Level next = levels.remove ( 0 );
        player.SetPosition ( next.getPlayerStartPosition () );
        for ( Enemy e:next.GetEnemies () ) {
            listeners.add ( e );
        }
        return next;
    }

    public Level getCurrLevel() {
        return currLevel;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
