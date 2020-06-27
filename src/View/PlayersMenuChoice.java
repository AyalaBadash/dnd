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

    }
    @Override
    public void Print() {
        for ( Player toPrint:playersToChoose ) {
            System.out.println (toPrint.Describe ());
        }
    }

    public String PlayersCohice(char choice){
        String name;
        if(choice =='1')
            name = "JonSnow";
        else if (choice == '2')
            name = "TheHound";
        else if(choice=='3')
            name = "Melisandre";
        else if(choice == '4')
            name = "ThorosOfMyr";
        else if(choice == '5')
            name = "AryaStark";
        else if(choice == '6')
            name = "Bronn";
        else
            name = "Ygritte";
        System.out.println ("You Choose " + name );
        return name;
    }
}
