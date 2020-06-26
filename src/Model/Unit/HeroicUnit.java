package Model.Unit;

import Model.Unit.Enemy.Enemy;
import View.Turn;

import java.util.List;

public interface HeroicUnit {
    public Turn OnAbilityCast(List<Enemy> enemies);
}
