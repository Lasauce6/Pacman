package game.ghostStates;

import game.elements.Ghost;

import java.util.Random;

public class FrightenedMode extends GhostState {
    public FrightenedMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void eaten() {
        ghost.switchEatenMode();
    }

    @Override
    public void timerFrightenedModeOver() {
        ghost.switchChaseMode();
    }

    @Override
    public int[] getTargetPosition(){
        int[] position = new int[2];
        Random rand = new Random();
        boolean randomAxis = rand.nextBoolean();
        position[0] = ghost.getxPos() + (randomAxis ? (rand.nextInt(3) - 1) * ghost.getSize() : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? (rand.nextInt() - 1) * ghost.getSize() : 0);
        return position;
    }
}
