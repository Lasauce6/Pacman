package game.elements.superpacgum;

import game.Labyrinth;

import java.awt.*;

public class PurplePacGum extends SuperPacGum {
    public PurplePacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.MAGENTA);
    }

    @Override
    public void setEaten(boolean b) {
        if (b) {
            destroy();
            Labyrinth.addScore(300);
            // TODO : Pacman invisible
        }
    }
}
