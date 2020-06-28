package View;

import Model.Unit.Player.*;

import java.util.ArrayList;
import java.util.List;

public class PlayersMenuChoice implements Printer {

    List<Player> playersToChoose = new ArrayList<> ();

    public PlayersMenuChoice(){

        playersToChoose.add ( new Warrior ( 0, 0, Warriors.JonSnow ) );
        playersToChoose.add ( new Warrior ( 0, 0, Warriors.TheHound ) );
        playersToChoose.add ( new Mage ( 0, 0, Mages.Melisandre ) );
        playersToChoose.add ( new Mage ( 0, 0, Mages.ThorosOfMyr ) );
        playersToChoose.add ( new Rogue ( 0, 0, Rogues.AryaStark ) );
        playersToChoose.add ( new Rogue ( 0, 0, Rogues.Bronn ) );
        playersToChoose.add ( new Hunter(0,0, Hunters.Ygritte) );

    }
    @Override
    public void Print() {
        int counter = 1;
        for ( Player toPrint:playersToChoose ) {
            System.out.println ("" + counter +". " + toPrint.Describe ());
            counter ++;
        }
    }
}
