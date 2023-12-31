package game.elements;

import game.Labyrinth;
import game.Observer;
import game.elements.superpacgum.SuperPacGum;
import game.ghostStates.*;

import java.awt.*;

/**
 * Class Ghost
 */
public class Ghost extends MovingElement implements Observer {
    private GhostState state; // L'état du fantôme
    private final GhostState chaseMode; // Le mode de poursuite du fantôme
    private final GhostState eatenMode; // Lorsque le fantôme est mangé
    private final GhostState frightenedMode; // Lorsque le fantôme est effrayé
    private final GhostState houseMode; // Lorsque le fantôme est dans la maison
    private final Color color; // La couleur du fantôme
    private final int vulnerableSpeed; // La vitesse du fantôme quand il est vulnérable
    private final int initialXPos; // La position en x initiale du fantôme
    private final int initialYPos; // La position en y initiale du fantôme

    /**
     * Constructeur de Ghost
     * @param size la taille du fantôme
     * @param xPos la position en x du fantôme
     * @param yPos la position en y du fantôme
     * @param speed la vitesse du fantôme
     * @param color la couleur du fantôme
     */
    public Ghost(int size, int xPos, int yPos, int speed, Color color) {
        super(size, xPos, yPos, speed);

        chaseMode = new ChaseMode(this);
        eatenMode = new EatenMode(this);
        frightenedMode = new FrightenedMode(this);
        houseMode = new HouseMode(this);

        state = houseMode;

        initialXPos = xPos;
        initialYPos = yPos;

        this.xVel = 0;
        this.yVel = 0;
        this.color = color;
        this.vulnerableSpeed = speed / 2;
        updateDirection();
    }


    // Méthodes pour changer les états du fantôme
    public void switchChaseMode() {
        state = chaseMode;
    }
    public void switchEatenMode() {
        state = eatenMode;
    }
    public void switchFrightenedMode() {
        state = frightenedMode;
    }
    public void switchHouseMode() {
        state = houseMode;
    }

    /**
     * Méthode qui permet de récupérer l'état du fantôme
     * @return l'état du fantôme
     */
    public GhostState getGhostState() {return state;}

    /**
     * Méthode qui permet de créer l'affichage du fantôme
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        if (state == eatenMode) g.setColor(Color.WHITE);
        else if (state == frightenedMode) g.setColor(Color.BLUE);
        else g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
    }

    /**
     * Méthode qui permet de mettre à jour le fantôme
     */
    @Override
    public void update() {
        if (!Labyrinth.getFirstInput()) return; // Les fantômes ne bougent pas tant que le joueur n'a pas joué

        if (!onGameplayWindow()) return;

        if (xPos > 10 * size && xPos < 17 * size && yPos > 12 * size && yPos < 14 * size && state == eatenMode) {
            state.insideHouse();
        } else if (xPos > 12 * size && xPos < 15 * size && yPos > 10 * size && yPos < 12 * size && state == houseMode) {
            state.outsideHouse();
        }

        if (state == frightenedMode) speed = vulnerableSpeed;
        else speed = 2 * vulnerableSpeed;

        // Le fantôme calcule sa nouvelle position et est mis à jour
        state.getNextDirection();
        updatePosition();
    }

    /**
     * Méthode qui permet de récupérer si le fantôme est vulnérable
     * @return vrai si le fantôme est vulnérable, faux sinon
     */
    public boolean isVulnerable() {
        return state == frightenedMode;
    }

    /**
     * Méthode qui permet de récupérer si le fantôme est mangé
     * @return vrai si le fantôme est mangé, faux sinon
     */
    public boolean isEaten() {
        return state == eatenMode;
    }

    @Override
    public void updatePacGumEaten(PacGum pg) {}

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {}

    @Override
    public void updateGhostCollision(Ghost gh) {
        if (state == frightenedMode && gh == this) {
            state.eaten();
        }
    }

    @Override
    public void updatePacmanInvisible() {}

    @Override
    public void updatePacmanInvincible() {
        switchFrightenedMode();
    }

    @Override
    public void timerPacmanInvisibleOver() {}

    @Override
    public void timerPacmanInvincibleOver() {
        state.timerFrightenedModeOver();
    }

    @Override
    public void updateLabyrinthChange() {}

    public void reset() {
        xPos = initialXPos;
        yPos = initialYPos;
        state = houseMode;
        xVel = 0;
        yVel = 0;
    }
}
