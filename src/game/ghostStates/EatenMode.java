package game.ghostStates;

import game.elements.Ghost;

/**
 * Class EatenMode
 */
public class EatenMode extends GhostState {
    public EatenMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void insideHouse() {
        ghost.switchHouseMode();
    }

    @Override
    public int[] getTargetPosition() {
        int[] targetPosition = new int[2];
        targetPosition[0] = (int) 13.5 * 24;
        targetPosition[1] = 13 * 24;
        return targetPosition;
    }
}
