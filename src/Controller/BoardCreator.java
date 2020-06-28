package Controller;

import Model.Unit.Player.Player;
import View.Level;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BoardCreator {

    private char playerInputChar;
    private String path;
    private Player currPlayer;

    public BoardCreator(char playerInputChar, String path)
    {
        PlayerCreator pc = new PlayerCreator ();
        this.playerInputChar = playerInputChar;
        currPlayer = (Player)pc.Create ( playerInputChar, -1,-1 );
        this.path = path;
    }

    //returns list of Levels
    public List<Level> ReadFromFolder(){
        List<Level> levels = new ArrayList<> ();
        File folder = new File ( path );
        String[] files = folder.list ();
        for (String level: files ) {
            levels.add ( Translate ( level ) );
        }
        return levels;
    }

    //translate each level at a time
    public Level Translate(String levelToRead) {
        List<String> levelLines = new ArrayList<> ( );
        try {
            levelLines = Files.readAllLines ( Paths.get ( path + "\\" + levelToRead ) );
        }
        catch (IOException e)
        {
            System.out.println (e.getMessage () );
        }
        Level level = new Level ( currPlayer );
        currPlayer = level.CreateBoardToLevel (levelLines);
        return level;
    }

    public Player GetPlayer(){
        return currPlayer;
    }

}
