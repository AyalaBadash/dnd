package Controller;

import Model.Unit.Player.Player;
import View.GameBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BoardCreator {

    private char  player;
    private String path;
    private Player currPlayer;

    public BoardCreator(char player, String path)
    {
        this.player = player;
        this.path = path;
    }

    public List<Level> ReadFromFolder(){
        List<Level> levels = new ArrayList<> ();
        File folder = new File ( path );
        String[] files = folder.list ();
        for (String level: files ) {
            levels.add ( Translate ( level ) );
        }
        return levels;
    }

    public Level Translate(String levelToRead) {
        List<String> levelLines = new ArrayList<> ( );
        try {
            levelLines = Files.readAllLines ( Paths.get ( path + "\\" + levelToRead ) );
        }
        catch (IOException e)
        {
            System.out.println (e.getMessage () );
        }
        Level level = new Level (player);
        currPlayer = level.CreateBoardToLevel (levelLines);
        return level;
    }

    public Player GetPlayer(){
        return currPlayer;
    }

}
