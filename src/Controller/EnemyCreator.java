package Controller;

import Model.Tile.Tile;
import Model.Unit.Enemy.*;

public class EnemyCreator implements Creator {

    @Override
    public Tile Create(char toCreate, int x, int y) {
        if(toCreate == 's')
            return new Monster (x,y, Minions.LannisterSoldier ) ;
        if(toCreate == 'k')
            return new Monster ( x,y,Minions.LannisterKnight );
        if(toCreate == 'q')
            return new Monster ( x,y,Minions.QueensGuard );
        if(toCreate == 'z')
            return new Monster ( x,y,Minions.Wright );
        if(toCreate == 'b')
            return new Monster ( x,y,Minions.BearWright );
        if(toCreate == 'g')
            return new Monster ( x,y,Minions.GiantWright );
        if(toCreate == 'w')
            return new Monster ( x,y,Minions.WhiteWalker );
        if(toCreate == 'M')
            return new Monster ( x,y,Minions.TheMountain );
        if(toCreate == 'C')
            return new Monster ( x,y,Minions.QueenCersei );
        if(toCreate == 'K')
            return new Monster ( x,y,Minions.NightsKing );
        if(toCreate == 'B')
            return new Trap ( x,y, Traps.BonusTrap );
        if(toCreate == 'Q')
            return new Trap ( x,y, Traps.QueensTrap );
        if(toCreate == 'D')
            return new Trap ( x,y, Traps.DeathTrap );
        else return null;
    }
}
