package Controller;

import Model.Tile.Tile;
import Model.Unit.Enemy.*;

public class EnemyCreator implements Creator {

    @Override
    public Tile Create(char toCreate, int x, int y) {
        if(toCreate == 's')
            return new Minion (x,y, Minions.LannisterSoldier ) ;
        if(toCreate == 'k')
            return new Minion ( x,y,Minions.LannisterKnight );
        if(toCreate == 'q')
            return new Minion ( x,y,Minions.QueensGuard );
        if(toCreate == 'z')
            return new Minion ( x,y,Minions.Wright );
        if(toCreate == 'b')
            return new Minion ( x,y,Minions.BearWright );
        if(toCreate == 'g')
            return new Minion ( x,y,Minions.GiantWright );
        if(toCreate == 'w')
            return new Minion ( x,y,Minions.WhiteWalker );
        if(toCreate == 'M')
            return new Boss ( x,y,Bosses.TheMountain );
        if(toCreate == 'C')
            return new Boss ( x,y,Bosses.QueenCersei );
        if(toCreate == 'K')
            return new Boss ( x,y,Bosses.NightsKing );
        if(toCreate == 'B')
            return new Trap ( x,y, Traps.BonusTrap );
        if(toCreate == 'Q')
            return new Trap ( x,y, Traps.QueensTrap );
        if(toCreate == 'D')
            return new Trap ( x,y, Traps.DeathTrap );
        else return null;
    }
}
