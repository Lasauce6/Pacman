package game.elements.superpacgum;

import game.Labyrinth;

import java.awt.*;

/**
 * Class BluePacGum
 */
public class BluePacGum extends SuperPacGum {
    public BluePacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.BLUE);
    }

    @Override
    public void setEaten(boolean b) {
        if (b) {
            destroy();
            Labyrinth.addScore(100);
        }
    }

    @Override
    public void notifyObserverPacmanInvisible() {}

    @Override
    public void notifyObserverPacmanInvincible() {}

    @Override
    public void notifyObserverTimerPacmanInvincibleOver() {}

    @Override
    public void notifyObserverTimerPacmanInvisibleOver() {}

    @Override
    public void notifyObserverLabyrinthChange() {}
}
