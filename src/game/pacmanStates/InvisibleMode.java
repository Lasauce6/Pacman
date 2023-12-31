package game.pacmanStates;

import game.elements.Pacman;

public class InvisibleMode extends PacmanState {
    public InvisibleMode(Pacman pacman) {
        super(pacman);
    }

    @Override
    public void timerOver() {
        pacman.switchNormalMode();
    }
}
