package game;

public interface Sujet {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserverPacGumEaten(game.elements.PacGum pg);
    void notifyObserverSuperPacGumEaten(game.elements.superpacgum.SuperPacGum spg);
    void notifyObserverGhostCollision(game.elements.Ghost g);
    void notifyObserverPacmanInvisible();
    void notifyObserverPacmanInvincible();
    void notifyObserverLabyrinthChange();
}
