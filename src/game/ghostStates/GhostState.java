package game.ghostStates;

import game.elements.Ghost;
import game.utils.Utils;
import game.utils.WallCollisionDetector;

/**
 * Classe abstraite pour les différents états d'un fantôme
 */
public abstract class GhostState {
    protected Ghost ghost;

    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    public void timerFrightenedModeOver() {}
    public void eaten() {}
    public void outsideHouse() {}
    public void insideHouse() {}

    // Méthodes pour les déplacements
    public int[] getTargetPosition() {
        return new int[2];
    }

    /**
     * Méthode pour obtenir la prochaine direction du fantôme en fonction de sa cible
     */
    public void getNextDirection() {
        int new_xSpd = 0;
        int new_ySpd = 0;

        double minDist = Double.MAX_VALUE;
        int[] targetPosition = getTargetPosition();

        if (ghost.getxVel() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, -ghost.getSpd(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getSpd(), ghost.getyPos(), targetPosition[0], targetPosition[1]);
            if (distance < minDist) {
                new_xSpd = -ghost.getSpd();
                minDist = distance;
            }
        }

        if (ghost.getxVel() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, ghost.getSpd(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getSpd(), ghost.getyPos(), targetPosition[0], targetPosition[1]);
            if (distance < minDist) {
                new_xSpd = ghost.getSpd();
                minDist = distance;
            }
        }

        if (ghost.getyVel() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, -ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getSpd(), targetPosition[0], targetPosition[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = -ghost.getSpd();
                minDist = distance;
            }
        }

        if (ghost.getyVel() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getSpd(), targetPosition[0], targetPosition[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = ghost.getSpd();
            }
        }

        if (new_xSpd == 0 && new_ySpd == 0) return;

        if (!WallCollisionDetector.checkWallCollision(ghost, new_xSpd, new_ySpd)) {
            ghost.setxVel(new_xSpd);
            ghost.setyVel(new_ySpd);
            System.out.println("new_xSpd = " + new_xSpd + " new_ySpd = " + new_ySpd + " ghost = " + ghost);
        } else {
            System.out.println("Collision" + ghost);
            ghost.setxVel(0);
            ghost.setyVel(0);
        }
    }

}
