package game.elements.superpacgum;

import game.Labyrinth;

import java.awt.*;

public class GreenPacGum extends SuperPacGum {
    public GreenPacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.GREEN);
    }

    @Override
    public void setEaten(boolean b) {
        if  (b) {
            destroy();
            Labyrinth.addScore(1000);
            // TODO : Change Labyrinth
        }
    }
}
