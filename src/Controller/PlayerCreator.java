package Controller;

import Model.Tile.Tile;
import Model.Unit.Player.*;

public class PlayerCreator implements Creator {

    @Override
    public Tile Create(char toCreate, int x, int y) {

        if(toCreate == '1')
            return new Warrior ( x,y, Warriors.JonSnow );
        else if(toCreate == '2')
            return new Warrior ( x,y,Warriors.TheHound );
        else if(toCreate== '3')
            return new Mage ( x,y, Mages.Melisandre );
        else if(toCreate == '4')
            return new Mage ( x,y,Mages.ThorosOfMyr );
        else if(toCreate == '5')
            return new Rogue ( x,y,Rogues.AryaStark );
        else if(toCreate == '6')
            return new Rogue ( x,y,Rogues.Bronn );
        return null;
    }
}
