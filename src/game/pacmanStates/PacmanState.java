package game.pacmanStates;

import game.elements.Pacman;

/**
 * Classe abstraite pour les différents états de Pacman
 */
public abstract class PacmanState {
    protected Pacman pacman;

    public PacmanState(Pacman pacman) {
        this.pacman = pacman;
    }

    // Transitions d'un état à un autre
    public void timerOver() {}

}
