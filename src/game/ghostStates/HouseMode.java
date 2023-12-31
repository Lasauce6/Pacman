package game.ghostStates;

import game.elements.Ghost;

public class HouseMode extends GhostState {
    public HouseMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void outsideHouse() {
        ghost.switchChaseMode();
    }

    @Override
    public int[] getTargetPosition() {
        int[] targetPosition = new int[2];
        targetPosition[0] =(int) 13.5 * 24;
        targetPosition[1] = 11 * 24;
        return targetPosition;
    }
}
