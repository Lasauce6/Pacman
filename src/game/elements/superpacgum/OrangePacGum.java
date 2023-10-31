package game.elements.superpacgum;

import game.Labyrinth;

import java.awt.*;

public class OrangePacGum extends SuperPacGum {
    public OrangePacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.ORANGE);
    }

    @Override
    public void setEaten(boolean b) {
        if (b) {
            destroy();
            Labyrinth.addScore(500);
            // TODO : Pacman superpacman
        }

    }
}
