package game.elements.superpacgum;

import game.Observer;
import game.Sujet;
import game.elements.StaticElement;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class SuperPacGum
 */
public abstract class SuperPacGum extends StaticElement implements Sujet {
    final ArrayList<Observer> observers = new ArrayList<>(); // La liste des observateurs
    private final Color color; // La couleur du SuperPacGum

    /**
     * Constructeur de SuperPacGum
     * @param size la taille du SuperPacGum
     * @param xPos la position en x du SuperPacGum
     * @param yPos la position en y du SuperPacGum
     * @param color la couleur du SuperPacGum
     */
    public SuperPacGum(int size, int xPos, int yPos, Color color) {
        super(size, xPos, yPos);
        this.color = color;
    }

    /**
     * Méthode qui permet changer si le SuperPacGum est mangé ou non
     * @param b vrai si le SuperPacGum est mangé, faux sinon
     */
    public abstract void setEaten(boolean b);

    /**
     * Méthode qui permet de dessiner le SuperPacGum
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval(xPos + 3, yPos + 3, size - 6, size - 6);
    }

    /**
     * Méthode qui permet d'ajouter un observateur
     * @param observer l'observateur à ajouter
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Méthode qui permet de supprimer un observateur
     * @param observer l'observateur à supprimer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Méthode qui permet de notifier les observateurs que le PacGum a été mangé
     */
    @Override
    public void notifyObserverPacGumEaten(game.elements.PacGum pg) {}

    /**
     * Méthode qui permet de notifier les observateurs que le SuperPacGum a été mangé
     */
    @Override
    public void notifyObserverSuperPacGumEaten(SuperPacGum spg) {}
    @Override
    public void notifyObserverGhostCollision(game.elements.Ghost g) {}
}
