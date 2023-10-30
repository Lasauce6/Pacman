package game;

import game.elements.Ghost;
import game.elements.PacGum;
import game.elements.SuperPacGum;

public interface Observer {
    void updatePacGumEaten(PacGum pg);
    void updateSuperPacGumEaten(SuperPacGum spg);
    void updateGhostCollision(Ghost gh);
}
