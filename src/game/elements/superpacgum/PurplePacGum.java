package game.elements.superpacgum;

import game.Labyrinth;
import game.Observer;

import java.awt.*;

/**
 * Class PurplePacGum
 */
public class PurplePacGum extends SuperPacGum {
    public PurplePacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.MAGENTA);
    }

    @Override
    public void setEaten(boolean b) {
        if (b) {
            destroy();
            Labyrinth.addScore(300);
            notifyObserverPacmanInvisible();
        }
    }
    @Override
    public void notifyObserverPacmanInvisible() {
        for (Observer observer : observers) {
            observer.updatePacmanInvisible();
        }
    }

    @Override
    public void notifyObserverPacmanInvincible() {

    }

    @Override
    public void notifyObserverLabyrinthChange() {

    }
}
