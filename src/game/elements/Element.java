package game.elements;

import java.awt.*;

/**
 * Class Element
 */
public abstract class Element {
    protected int size; // La taille de l'élément
    protected int xPos; // La position en x de l'élément
    protected int yPos; // La position en y de l'élément

    protected boolean destroyed = false; // Si l'élément est détruit ou non

    /**
     * Constructeur de Element
     * @param size la taille de l'élément
     * @param xPos la position en x de l'élément
     * @param yPos la position en y de l'élément
     */
    public Element(int size, int xPos, int yPos) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Méthode qui met à jour l'élément
     */
    public void update() {}

    /**
     * Méthode qui permet de dessiner l'élément
     * @param g l'objet graphique
     */
    public void render(Graphics2D g) {}

    /**
     * Méthode qui permet de détruire l'élément
     */
    public void destroy() {
        this.xPos = -32;
        this.yPos = -32;
        destroyed = true;
    }

    /**
     * Méthode qui permet de savoir si l'élément est détruit ou non
     * @return vrai si l'élément est détruit, faux sinon
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Méthode qui permet de récupérer la taille de l'élément
     * @return la taille de l'élément
     */
    public int getSize() {
        return size;
    }

    /**
     * Méthode qui permet de récupérer la position en x de l'élément
     * @return la position en x de l'élément
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Méthode qui permet de récupérer la position en y de l'élément
     * @return la position en y de l'élément
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Méthode qui permet de récupérer le rectangle de collision
     * @return le rectangle de collision
     */
    public abstract Rectangle getHitbox();
}
