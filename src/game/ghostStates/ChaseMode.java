package game.ghostStates;

import game.Labyrinth;
import game.elements.Ghost;

import java.util.Random;

public class ChaseMode extends GhostState {
    public ChaseMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public int[] getTargetPosition() {
        int[] position = new int[2];
        Random rand = new Random();
        boolean randomAxis = rand.nextBoolean();
        position[0] = ghost.getxPos() + (randomAxis ? (rand.nextInt(3) - 1) * ghost.getSize() : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? (rand.nextInt() - 1) * ghost.getSize() : 0);
        return position;
    }

}
