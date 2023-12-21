package game;

import game.elements.Ghost;
import game.elements.PacGum;
import game.elements.superpacgum.SuperPacGum;

/**
 * Interface Observer
 */
public interface Observer {
    void updatePacGumEaten(PacGum pg);
    void updateSuperPacGumEaten(SuperPacGum spg);
    void updateGhostCollision(Ghost gh);
    void updatePacmanInvisible();
    void updatePacmanInvincible();
    void updateLabyrinthChange();
}
