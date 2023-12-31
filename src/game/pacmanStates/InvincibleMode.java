package game.pacmanStates;

import game.elements.Pacman;

public class InvincibleMode extends PacmanState {
    public InvincibleMode(Pacman pacman) {
        super(pacman);
    }

    @Override
    public void timerOver() {
        pacman.switchNormalMode();
    }
}
