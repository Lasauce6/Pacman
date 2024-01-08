package game.utils;

/**
 * Class Utils
 */
public class Utils {

    //Fonction pour obtenir la distance entre deux points
    public static double getDistance(double xA, double yA, double xB, double yB) {
        return Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
    }
}
