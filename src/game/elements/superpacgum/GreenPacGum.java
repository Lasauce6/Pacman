package game.elements.superpacgum;

import game.Labyrinth;
import game.Observer;

import java.awt.*;

/**
 * Class GreenPacGum
 */
public class GreenPacGum extends SuperPacGum {
    public GreenPacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.GREEN);
    }

    @Override
    public void setEaten(boolean b) {
        if  (b) {
            destroy();
            Labyrinth.addScore(1000);
            notifyObserverLabyrinthChange();
        }
    }

    @Override
    public void notifyObserverPacmanInvisible() {

    }

    @Override
    public void notifyObserverPacmanInvincible() {

    }

    @Override
    public void notifyObserverTimerPacmanInvincibleOver() {

    }

    @Override
    public void notifyObserverTimerPacmanInvisibleOver() {

    }

    @Override
    public void notifyObserverLabyrinthChange() {
        for (Observer observer : observers) {
            observer.updateLabyrinthChange();
        }
    }
}
